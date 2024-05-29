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
    @Autowired // Autowired annotation is used here for dependency injection.
    public CustomerRepository customerRepositiory;
    // This method saves a customer to the repository.
    public CustomerModel saveCustomer(CustomerModel customer) {
        if (customer == null) {
            throw new ObjectIsNull("The Value Of object is null");
        }
        return customerRepositiory.saveCustomer(customer);
    }
    // This method retrieves all customers from the repository.
    public List<CustomerModel> getCustomer() {
        if (customerRepositiory.getCustomer().isEmpty()) {
            throw new UnsupportedOperationException("Unimplemented method 'getCustomer'");
        }
        return customerRepositiory.getCustomer();
    }
    // This method updates a customer's data in the repository.
    public CustomerModel updateData(int id, String name) {
        if (!customerRepositiory.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present");
        }
        return customerRepositiory.updateData(id, name);
    }
    // This method retrieves all customer IDs from the repository.
    public List<Integer> getId() {
        return customerRepositiory.getId();
    }
    // This method deletes a customer's data from the repository.
    public void deleteData(int id) {
        if (!customerRepositiory.getId().contains(id)) {
            throw new DataIsNotPresent("Data Is Not Present For Delete");
        }
        customerRepositiory.deleteData(id);
    }
    // This method updates a customer's salary in the repository.
    public CustomerModel updateSal(int id, int sal){
        if (!customerRepositiory.getId().contains(id)) {
            throw new DataIsNotPresent("The Id Is Not Present");
        }
        return customerRepositiory.updateSal(id, sal);
    }
}
