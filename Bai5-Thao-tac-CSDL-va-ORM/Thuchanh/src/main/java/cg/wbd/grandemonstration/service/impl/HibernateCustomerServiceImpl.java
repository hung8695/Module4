package cg.wbd.grandemonstration.service.impl;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static java.util.Arrays.asList;

public class HibernateCustomerServiceImpl  implements CustomerService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    private static List<Customer> customers;
    private static long autoIncreaseId = 0;

    static {
        customers = asList(
                new Customer(autoIncreaseId++, "T", "t@codegym.vn", "Da Nang"),
                new Customer(autoIncreaseId++, "Nhat", "nhat@codegym.vn", "Quang Tri"),
                new Customer(autoIncreaseId++, "Trang", "trang@codegym.vn", "Ha Noi"),
                new Customer(autoIncreaseId++, "Nguyen Binh Son", "son@codegym.vn", "Sai Gon"),
                new Customer(autoIncreaseId++, "Dang Xuan Hoa", "hoa.dang@codegym.vn", "Da Nang")
        );
    }

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM  Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr,Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findOne(Long id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr,Customer.class);
        query.setParameter("id",id);
        return query.getSingleResult();

    }

    @Override
    public Customer save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer origin = findOne(customer.getId());
            origin.setName(customer.getName());
            origin.setAddress(customer.getAddress());
            origin.setEmail(customer.getEmail());
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Customer> save(List<Customer> customers) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void delete(List<Customer> customers) {

    }

    @Override
    public void deleteAll() {

    }
}
