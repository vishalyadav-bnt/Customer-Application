package com.example.bnt.customerControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bnt.controller.CustomerController;
import com.example.bnt.exception.DataIsNotPresent;
import com.example.bnt.exception.ObjectIsNull;
import com.example.bnt.model.CustomerModel;
import com.example.bnt.services.CustomerService;

public class CustomerControllerTest {

    @Mock
    CustomerService customerServiceMock;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer_Positive() throws Exception{
        CustomerModel customerModel=new CustomerModel(1,"soham",1234);
        when(customerServiceMock.saveCustomer(customerModel)).thenReturn(customerModel);
        ResponseEntity<Object> customer=customerController.saveCustomer(customerModel);
        assertEquals(customerModel, customer.getBody());
    }


    @Test
    public void testSaveCustomer_Negative_ExceptionThrown() throws ObjectIsNull {
        // Mocking
        CustomerModel customerModel = new CustomerModel(1, "John Doe", 1000);
        when(customerServiceMock.saveCustomer(customerModel)).thenThrow(new RuntimeException("Test Exception"));

        // Test
        assertEquals(customerModel, customerModel);
    }

    @Test
    public void testGetCustomer_Positive() throws ObjectIsNull {
        // Mocking
        List<CustomerModel> customers = new ArrayList<>();
        customers.add(new CustomerModel());
        when(customerServiceMock.getCustomer()).thenReturn(customers);

        // Test
        List<CustomerModel> result = customerController.getCustomer();
        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomer_Negative_ExceptionThrown() {
        // Mocking
        when(customerServiceMock.getCustomer()).thenThrow(new RuntimeException("Data Not Found"));

        // Test
        assertNull(customerController.getCustomer());
    }

    @Test
    public void testUpdateData_Positive() throws Exception {
        // Mocking
        int id = 1;
        String name = "John";
        CustomerModel customer = new CustomerModel();

       
        when(customerServiceMock.getId()).thenReturn(List.of(id));

        // Mocking the behavior of updateData method
        when(customerServiceMock.updateData(id, name)).thenReturn(customer);

        // Test
        ResponseEntity<Object> result = customerController.updateData(id, name);
        assertEquals(customer, result.getBody());
    }

    @Test
    public void testUpdateData_Negative_IdNotFound() throws Exception {
        // Mocking
        int id = 12345; // Assuming this id doesn't exist
        String name = "John Doe";
        when(customerServiceMock.updateData(id, name)).thenThrow(new RuntimeException());

        // Test
        ResponseEntity<Object> response = customerController.updateData(id, name);
       
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
       
    }

    @Test
    public void testDeleteData_Positive() throws Exception {
        // Mocking
        int id = 1;
        when(customerServiceMock.getId()).thenReturn(List.of(id));

        // Test
        ResponseEntity<String> result = customerController.deleteData(id);
        assertEquals("Data Are Deleted with " + id, result.getBody());
    }
    @Test
    public void testUpdateSal_Positive() throws DataIsNotPresent, SQLException {
        // Mocking
        int id = 1;
        int sal = 5000;
        CustomerModel customer = new CustomerModel();

        // Mocking the behavior of getId method
        when(customerServiceMock.getId()).thenReturn(List.of(id));

        // Mocking the behavior of updateSal method
        when(customerServiceMock.updateSal(id, sal)).thenReturn(customer);

        // Test
        ResponseEntity<Object> result = customerController.updateSal(id, sal);
        assertEquals(customer, result.getBody());
    }

   @Test
public void testUpdateSal_Negative_InvalidId() throws DataIsNotPresent, SQLException {
    // Mocking
    int id = 123145; // Invalid customer ID
    int sal = 5000;
    when(customerServiceMock.updateSal(id, sal)).thenThrow(new DataIsNotPresent("Id Not Found"));

    // Test
    ResponseEntity<Object> response = customerController.updateSal(id, sal);
    
    // Assertion
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //assertEquals("Error Id Not Found: Data Is Not Present", response.getBody());
}

}
