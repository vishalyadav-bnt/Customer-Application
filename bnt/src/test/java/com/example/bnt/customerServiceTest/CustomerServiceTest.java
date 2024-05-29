package com.example.bnt.customerServiceTest;

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

import com.example.bnt.dao.CustomerRepository;
import com.example.bnt.exception.DataIsNotPresent;
import com.example.bnt.exception.ObjectIsNull;
import com.example.bnt.model.CustomerModel;
import com.example.bnt.services.CustomerService;

public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepositoryMock;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer_Positive() throws ObjectIsNull {

        CustomerModel customer = new CustomerModel(1, "John", 50000);
        when(customerRepositoryMock.saveCustomer(customer)).thenReturn(customer);

        CustomerModel result = customerService.saveCustomer(customer);
        assertEquals(customer, result);
    }

    @Test
    public void testSaveCustomer_Negative_NullInput() {

        assertThrows(ObjectIsNull.class, () -> {
            customerService.saveCustomer(null);
        });
    }

    @Test
    public void testGetCustomer_Positive() {

        List<CustomerModel> customers = new ArrayList<>();
        customers.add(new CustomerModel());
        when(customerRepositoryMock.getCustomer()).thenReturn(customers);

        List<CustomerModel> result = customerService.getCustomer();
        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomer_Negative_ResponseIsNull() {

        when(customerRepositoryMock.getCustomer()).thenReturn(new ArrayList<>());

        assertThrows(UnsupportedOperationException.class, () -> {
            customerService.getCustomer();
        });
    }

    @Test
    public void testUpdateData_Positive() throws Exception {

        int id = 1;
        String name = "John";
        CustomerModel customer = new CustomerModel();

        when(customerRepositoryMock.getId()).thenReturn(List.of(id));

        when(customerRepositoryMock.updateData(id, name)).thenReturn(customer);

        CustomerModel result = customerService.updateData(id, name);
        assertEquals(customer, result);
    }

    @Test
    public void testUpdateData_Negative_InvalidId() {

        int id = 1;
        String name = "John";
        when(customerRepositoryMock.getId()).thenReturn(new ArrayList<>());

        assertThrows(DataIsNotPresent.class, () -> {
            customerService.updateData(id, name);
        });
    }

    @Test
    public void testDeleteData_Positive() throws Exception {

        int id = 1;
        when(customerRepositoryMock.getId()).thenReturn(List.of(id));

        customerService.deleteData(id);

    }

    @Test
    public void testDeleteData_Negative_InvalidId() {

        int id = 1;
        when(customerRepositoryMock.getId()).thenReturn(new ArrayList<>());

        assertThrows(DataIsNotPresent.class, () -> {
            customerService.deleteData(id);
        });
    }

    @Test
    public void testUpdateSal_Positive() throws DataIsNotPresent, SQLException {

        int id = 1;
        int sal = 5000;
        CustomerModel customer = new CustomerModel();

        when(customerRepositoryMock.getId()).thenReturn(List.of(id));

        when(customerRepositoryMock.updateSal(id, sal)).thenReturn(customer);

        CustomerModel result = customerService.updateSal(id, sal);
        assertEquals(customer, result);
    }

    @Test
    public void testUpdateSal_Negative_InvalidId() {

        int id = 1;
        int sal = 5000;
        when(customerRepositoryMock.getId()).thenReturn(new ArrayList<>());

        assertThrows(DataIsNotPresent.class, () -> {
            customerService.updateSal(id, sal);
        });
    }
}
