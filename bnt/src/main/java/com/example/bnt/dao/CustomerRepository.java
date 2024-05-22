package com.example.bnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import com.example.bnt.dbConfig.DatabaseConfig;
import com.example.bnt.model.CustomerModel;
@Repository
public class CustomerRepository {

 @Autowired
 DataSource dataSource;

    public CustomerModel saveCustomer(CustomerModel customer){
        if(customer==null){
            throw new NullPointerException("Object is null");
        }
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer (id, name,sal) VALUES (?, ?, ?)");
            statement.setInt(1, customer.getId());
            statement.setString(2,customer.getName()); 
            statement.setInt(3,customer.getSal());
            statement.executeUpdate();
                statement.close();
                connection.close();
    
        }
        catch(Exception e){
            System.out.println("Empty Object");
        }
        return customer;
    }


    public List<CustomerModel> getCustomer()
    {
        List<CustomerModel>list=new ArrayList<CustomerModel>();
        try {
            Connection conn=dataSource.getConnection();
           
            PreparedStatement stmt=conn.prepareStatement("select *from customer");
            ResultSet rs=stmt.executeQuery();
          
            while(rs.next())
            {
                CustomerModel customer=new CustomerModel();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setSal(rs.getInt(3));
                list.add(customer);

            }
            
        } catch (Exception e) {
           System.out.println("Empty Object");
        }
       
        return list;
    }
}
