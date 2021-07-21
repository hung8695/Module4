package com.hihi.repository.impl;

import com.hihi.model.Customer;
import com.hihi.repository.IGeneralRepository;

import java.util.List;

public class GeneralRepositoryImpl implements IGeneralRepository<Customer> {
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(long id) {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void remove(long id) {

    }
}
