package com.example.bnt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bnt.dao.CustomerRepository;
import com.example.bnt.exception.DataIsNotPresent;
import com.example.bnt.exception.ObjectIsNull;
import com.example.bnt.model.CustomerModel;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepositiory;

    public CustomerModel saveCustomer(CustomerModel customer) throws ObjectIsNull {
        if (customer == null) {
            throw new ObjectIsNull("The Vaule Of object is null");
        }
        return customerRepositiory.saveCustomer(customer);
    }

    public List<CustomerModel> getCustomer() {
        if (customerRepositiory.getCustomer().isEmpty()) {
            throw new UnsupportedOperationException("Unimplemented method 'getCustomer'");
        }
        return customerRepositiory.getCustomer();
    }

    public CustomerModel updateData(int id, String name) throws DataIsNotPresent {
        if (!customerRepositiory.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present");
        }
        return customerRepositiory.updateData(id, name);
    }

    public List<Integer> getId() {
        return customerRepositiory.getId();
    }

    public void deleteData(int id) throws DataIsNotPresent {
        if (!customerRepositiory.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present");
        }
        customerRepositiory.deleteData(id);
    }
}
