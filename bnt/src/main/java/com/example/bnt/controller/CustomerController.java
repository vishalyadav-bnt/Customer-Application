package com.example.bnt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bnt.exception.ObjectIsNull;
import com.example.bnt.model.CustomerModel;
import com.example.bnt.services.CustomerService;

@RestController
public class CustomerController {
    
@Autowired
CustomerService customerservice;

 @PostMapping("/customer")
public CustomerModel saveCustomer(@RequestBody CustomerModel customer)throws ObjectIsNull
{
    if(customer==null)
    {  
         throw new ObjectIsNull("The Value of Object is Null");
    }
    return customerservice.saveCustomer(customer);
}

@GetMapping("/getData")
public java.util.List<CustomerModel> getCustomer() throws ObjectIsNull
{
    java.util.List<CustomerModel> customers = customerservice.getCustomer();
    if(customers.isEmpty())
    {
        throw new ObjectIsNull("The Value of Object is Null");
    }
    return customers;

}

}
