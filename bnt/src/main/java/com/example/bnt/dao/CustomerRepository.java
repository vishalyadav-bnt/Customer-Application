package com.example.bnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.bnt.model.CustomerModel;

@Repository
public class CustomerRepository {
    @Autowired // Autowired annotation is used here for dependency injection.
    DataSource dataSource;

    // This method saves a customer to the database.
    public CustomerModel saveCustomer(CustomerModel customer) {
        if (customer == null) {
            throw new NullPointerException("Object is null");
        }
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO customer (id, name, sal) VALUES (?, ?, ?)")) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getSal());
            statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Error occurred while saving customer: " + exception.getMessage());
        }
        return customer;
    }

    // This method retrieves all customers from the database.
    public List<CustomerModel> getCustomer() {
        List<CustomerModel> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setSal(rs.getInt(3));
                list.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving customers: " + e.getMessage());
        }
        return list;
    }
    // This method updates a customer's name based on their ID.
    public CustomerModel updateData(int id, String name) {
        CustomerModel cust = new CustomerModel();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement("UPDATE customer SET name=? WHERE id=?");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM customer")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cust.setId(rs.getInt(1));
                cust.setName(rs.getString(2));
                cust.setSal(rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error is" + e);
        }
        return cust;
    }
    // This method retrieves all customer IDs from the database.
    public List<Integer> getId() {
        List<Integer> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id FROM customer");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // This method deletes a customer from the database based on their ID.
    public void deleteData(int id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // This method updates a customer's salary based on their ID.
    public CustomerModel updateSal(int id, int sal) throws SQLException {
        CustomerModel cust = new CustomerModel();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("update customer set sal=? where id=?");
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM customer")) {
            preparedStatement.setInt(1, sal);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cust.setId(resultSet.getInt(1));
                cust.setName(resultSet.getString(2));
                cust.setSal(resultSet.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cust;
    }

}
