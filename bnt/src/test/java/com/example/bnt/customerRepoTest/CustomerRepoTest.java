package com.example.bnt.customerRepoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.bnt.dao.CustomerRepository;

import com.example.bnt.model.CustomerModel;

public class CustomerRepoTest {

    @Mock
    DataSource dataSourceMock;

    @InjectMocks
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer_Positive() throws Exception {

        CustomerModel customer = new CustomerModel();
        Connection connectionMock = mock(Connection.class);
        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        CustomerModel result = customerRepository.saveCustomer(customer);
        assertEquals(customer, result);
    }

    @Test
    public void testSaveCustomer_Nagative() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            customerRepository.saveCustomer(null);
        });
    }

    @Test
    public void testGetCustomer_Positive() throws Exception {

        Connection connectionMock = mock(Connection.class);
        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        List<CustomerModel> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new CustomerModel(1, "John", 50000));
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getInt(1)).thenReturn(1);
        when(resultSetMock.getString(2)).thenReturn("John");
        when(resultSetMock.getInt(3)).thenReturn(50000);

        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);
        List<CustomerModel> result = customerRepository.getCustomer();
        assertIterableEquals(expectedCustomers, result);
    }
    // @Test
    // public void testGetCustomer_Negative() throws SQLException
    // {
    //  Connection connection=mock(Connection.class);
    //  //ResultSet resultSet=mock(ResultSet.class);
    //  PreparedStatement preparedStatement=mock(PreparedStatement.class);
    //  when(dataSourceMock.getConnection()).thenReturn(connection);
    //  when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    //  when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
    //  assertThrows(SQLException.class,()->
    //  {
    //     customerRepository.getCustomer();
    //  });


    
    @Test
    public void testDeleteData() throws SQLException {
        int id = 1;
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(dataSourceMock.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        customerRepository.deleteData(id);
        verify(dataSourceMock).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDelete_Nagative() throws SQLException {
        int id = 1;

        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(dataSourceMock.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("delete Faild"));

        customerRepository.deleteData(id);
        verify(dataSourceMock).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDataUpdate_positive() throws SQLException {
        int id = 1;
        String name = "Dinesh Chikle";

        Connection connectionMock = mock(Connection.class);
        PreparedStatement updateStatementMock = mock(PreparedStatement.class);
        PreparedStatement selectStatementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);

        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement("UPDATE customer SET name=? WHERE id=?")).thenReturn(updateStatementMock);
        when(connectionMock.prepareStatement("SELECT * FROM customer")).thenReturn(selectStatementMock);
        when(selectStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getInt(1)).thenReturn(id);
        when(resultSetMock.getString(2)).thenReturn(name);

        CustomerModel result = customerRepository.updateData(id, name);

        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        verify(updateStatementMock).setString(1, name);
        verify(updateStatementMock).setInt(2, id);
        verify(updateStatementMock).executeUpdate();
    }

    @Test
    public void testUpdateSal_Positive() throws SQLException {
        // Arrange
        int id = 1;
        int newSalary = 50000;

        // Mocking necessary components
        Connection connectionMock = mock(Connection.class);
        PreparedStatement updateStatementMock = mock(PreparedStatement.class);
        PreparedStatement selectStatementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);

        // Setting up mock interactions
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(updateStatementMock);
        when(connectionMock.prepareStatement("SELECT * FROM customer")).thenReturn(selectStatementMock);
        when(selectStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        when(resultSetMock.getInt(1)).thenReturn(id);
        when(resultSetMock.getInt(3)).thenReturn(newSalary);

        // Act
        CustomerModel result = customerRepository.updateSal(id, newSalary);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newSalary, result.getSal());
        verify(updateStatementMock).setInt(1, newSalary);
        verify(updateStatementMock).setInt(2, id);
        verify(updateStatementMock).executeUpdate();
    }

    
}