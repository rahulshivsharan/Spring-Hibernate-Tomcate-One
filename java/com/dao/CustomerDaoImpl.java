/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Customer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;



@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao{

    @Autowired
    @Qualifier("sf")
    private SessionFactory sessionFactory;
    
    @Override
    public List<Customer> getCustomers() throws Exception {         
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.getNamedQuery("customerListQuery");
        List<Customer> list = q.list();        
        return list;
    }

    @Override    
    public void saveCustomer(Customer customer) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Override    
    public Customer getCustomer(Integer customerId) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.get(Customer.class, customerId);
        return customer;
    }
    
    @Override
    public void updateCustomer(Customer customer) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
