package com.example.bnt.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bnt.model.CustomerModel;
import com.example.bnt.services.CustomerService;
import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerservice;
    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerModel customer) {
        LOGGER.info("Saving customer: {}", customer);
        CustomerModel customerModel = customerservice.saveCustomer(customer);
        return (customerModel != null) ? ResponseEntity.ok(customerModel) :
        ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Is Null");
    }
    @GetMapping
    public List<CustomerModel> getCustomer() {
        LOGGER.info("Fetching all customers");
        return customerservice.getCustomer();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") int id, @RequestParam("name") String name) {
        LOGGER.info("Updating customer with ID: {} and name: {}", id, name);
        CustomerModel customerModel = customerservice.updateData(id, name);
        return ResponseEntity.ok(customerModel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") int id) {
        LOGGER.info("Deleting customer with ID: {}", id);
        customerservice.deleteData(id);
        return ResponseEntity.ok("Data Are Deleted with " + id);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSal(@PathVariable("id") int id, @RequestParam("sal") int sal) {
        LOGGER.info("Updating salary for customer with ID: {} to {}", id, sal);
        CustomerModel cust = customerservice.updateSal(id, sal);
        return ResponseEntity.ok(cust);
    }
}
