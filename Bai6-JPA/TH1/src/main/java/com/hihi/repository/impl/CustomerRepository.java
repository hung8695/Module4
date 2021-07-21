package com.hihi.repository.impl;

import com.hihi.model.Customer;
import com.hihi.repository.ICustomerRepository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c" , Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer  c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if(customer.getId()!=null){
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }

    @Override
    public void remove(long id) {
        Customer customer = findById(id);
        if(customer!=null){
            em.remove(customer);
        }
    }

    @Override
    public boolean insertWidthStoredProcedure(Customer customer) {
        String sql = "CALL insert_customer(:firstName, :lastName)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        return query.executeUpdate()==0;
    }
}
