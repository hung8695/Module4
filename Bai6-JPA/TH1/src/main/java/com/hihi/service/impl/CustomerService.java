package com.hihi.service.impl;
import com.hihi.model.Customer;
import com.hihi.repository.ICustomerRepository;
import com.hihi.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository  customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
       customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        customerRepository.remove(id);
    }

    @Override
    public boolean insertWidthStoredProcedure(Customer customer) {
        return customerRepository.insertWidthStoredProcedure(customer);
    }
}
