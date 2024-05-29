package com.example.bnt.controller;
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
    @Autowired
    CustomerService customerservice;
    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerModel customer){
        CustomerModel customerModell = customerservice.saveCustomer(customer);
        return(customerModell!=null)?ResponseEntity.ok(customerModell):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Is Null");
    }
    @GetMapping
    public List<CustomerModel> getCustomer() {
            return customerservice.getCustomer();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") int id, @RequestParam("name") String name) {
            CustomerModel customerModel = customerservice.updateData(id, name);
            return ResponseEntity.ok(customerModel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") int id) {
            customerservice.deleteData(id);
            return ResponseEntity.ok("Data Are Deleted with " + id);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSal(@PathVariable("id") int id, @RequestParam("sal") int sal) {
            CustomerModel cust = customerservice.updateSal(id, sal);
            return ResponseEntity.ok(cust);
    }
}
