/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.entity.Customer;
import java.util.List;


public interface CustomerService {
    List<Customer> getCustomers() throws Exception;
    
    List<Customer> saveCustomer(Customer customer) throws Exception;
    
    List<Customer> updateCustomer(Customer customer) throws Exception;
    
    Customer getCustomer(Integer customerId) throws Exception;
}
