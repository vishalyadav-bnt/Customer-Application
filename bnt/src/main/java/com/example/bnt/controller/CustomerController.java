package com.example.bnt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerModel customer) throws ObjectIsNull {
        try {
            CustomerModel customerModell=customerservice.saveCustomer(customer);
            return ResponseEntity.ok(customerModell);
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Is Null");
        }
    }
    @GetMapping
    public List<CustomerModel> getCustomer() {
        try {
            return customerservice.getCustomer();
        } catch (Exception e) {
            return null;
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") int id, @RequestParam("name") String name) {
        try {
            CustomerModel customerModel= customerservice.updateData(id, name);
            return ResponseEntity.ok(customerModel);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Id"+id+" not found."+e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") int id) {
        try {
            customerservice.deleteData(id);
            return ResponseEntity.ok("Data Are Deleted with " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Id "+id+" Not Found " + e.getMessage());
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSal(@PathVariable("id") int id, @RequestParam("sal") int sal) {
        try {
            CustomerModel cust=customerservice.updateSal(id, sal);
            return ResponseEntity.ok(cust);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Id "+id+" Not Found"+e.getMessage());
        }
    }
}
