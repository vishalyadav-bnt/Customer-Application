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
    public void testSaveCustomer_Positive() throws ObjectIsNull {
        // Mocking
        CustomerModel customer = new CustomerModel(1, "vishal", 120000);
        when(customerServiceMock.saveCustomer(customer)).thenReturn(customer);

        // Test
        CustomerModel result = customerController.saveCustomer(customer);
        assertEquals(customer, result);
    }

    @Test
    public void testSaveCustomer_Negative_NullInput() {
        // Test
        assertThrows(ObjectIsNull.class, () -> {
            customerController.saveCustomer(null);
        });
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
    public void testGetCustomer_Negative_ResponseIsNull() {
        // Mocking
        when(customerServiceMock.getCustomer()).thenReturn(new ArrayList<>());

        // Test
        assertThrows(ObjectIsNull.class, () -> {
            customerController.getCustomer();
        });
    }

    @Test
    public void testUpdateData_Positive() throws DataIsNotPresent {
        // Mocking
        int id = 1;
        String name = "John";
        CustomerModel customer = new CustomerModel();

       
        when(customerServiceMock.getId()).thenReturn(List.of(id));

        // Mocking the behavior of updateData method
        when(customerServiceMock.updateData(id, name)).thenReturn(customer);

        // Test
        CustomerModel result = customerController.updateData(id, name);
        assertEquals(customer, result);
    }

    @Test
    public void testUpdateData_Negative_InvalidId() {
        // Mocking
        int id = 1;
        String name = "John";
        when(customerServiceMock.getId()).thenReturn(new ArrayList<>());

        // Test
        assertThrows(DataIsNotPresent.class, () -> {
            customerController.updateData(id, name);
        });
    }

    @Test
    public void testDeleteData_Positive() throws DataIsNotPresent {
        // Mocking
        int id = 1;
        when(customerServiceMock.getId()).thenReturn(List.of(id));

        // Test
        ResponseEntity<String> result = customerController.deleteData(id);
        assertEquals("Data Are Deleted with " + id, result.getBody());
    }

    @Test
    public void testDeleteData_Negative_InvalidId() {
        // Mocking
        int id = 1;
        when(customerServiceMock.getId()).thenReturn(new ArrayList<>());

        // Test
        assertThrows(DataIsNotPresent.class, () -> {
            customerController.deleteData(id);
        });
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
        CustomerModel result = customerController.updateSal(id, sal);
        assertEquals(customer, result);
    }

    @Test
    public void testUpdateSal_Negative_InvalidId() {
        // Mocking
        int id = 1;
        int sal = 5000;
        when(customerServiceMock.getId()).thenReturn(new ArrayList<>());

        // Test
        assertThrows(DataIsNotPresent.class, () -> {
            customerController.updateSal(id, sal);
        });
    }
}
