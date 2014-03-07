/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Customer;
import com.service.CustomerService;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author rs69421
 */

@Controller("plainCtrl")
@RequestMapping(value = "/plain")
public class PlainController {
    
    @Autowired
    @Qualifier("customerService")
    private CustomerService service;
    
    
    @RequestMapping(value ="/text",method = RequestMethod.GET,produces = "text/html")
    public void getMsg(HttpServletRequest req, HttpServletResponse res){        
        StringBuffer strb = new StringBuffer();
        strb.append("<h4>Hi</h4>")
                .append("<br />")
                .append("<h4>Rahul Shivsharan </h4>");
        res.setContentType("text/html");
        PrintWriter out = null;
        try{
            
            out = res.getWriter();
            out.print(strb.toString());
        }catch(Exception e){
            e.printStackTrace();                   
        }
    }
    
    
    @RequestMapping(value = "/list", method= RequestMethod.GET,produces = "application/json")
    public @ResponseBody List<Customer> getAllCustomers(){
        List<Customer> list = null;
        try{
            list = service.getCustomers();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @return the service
     */
    public CustomerService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CustomerService service) {
        this.service = service;
    }
}
