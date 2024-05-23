package com.example.bnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bnt.exception.DataIsNotPresent;
import com.example.bnt.exception.ObjectIsNull;
import com.example.bnt.model.CustomerModel;
import com.example.bnt.services.CustomerService;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerservice;

    @PostMapping
    public CustomerModel saveCustomer(@RequestBody CustomerModel customer) throws ObjectIsNull {
        if (customer == null) {
            throw new ObjectIsNull("The Value of Object is Null");
        }
        return customerservice.saveCustomer(customer);
    }

    @GetMapping
    public List<CustomerModel> getCustomer() throws ObjectIsNull {
        List<CustomerModel> customers = customerservice.getCustomer();
        if (customers.isEmpty()) {
            throw new ObjectIsNull("The Value of Object is Null");
        }
        return customers;

    }

    @PutMapping
    public CustomerModel updateData(@RequestParam("id") int id, @RequestParam("name") String name)
            throws DataIsNotPresent {
        if (!customerservice.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present");
        }
        return customerservice.updateData(id, name);
    }

    @DeleteMapping
    public void deleteData(@RequestParam("id") int id) throws DataIsNotPresent {
        if (!customerservice.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present");
        }
        customerservice.deleteData(id);

    }

}
