/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author rs69421
 */

@WebServlet(name = "Testing",urlPatterns = "/test")
public class ConnectionTesting extends HttpServlet{
    
    private void testDBConnection(PrintWriter out) throws Exception{
        DataSource source = null;
        Context envCtx = null;
        Context ctx = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            envCtx = new InitialContext();
            ctx = (Context) envCtx.lookup("java:/comp/env");
            source = (DataSource) ctx.lookup("java/mydb");
            con = source.getConnection();           
//            out.println("Connection Done ? "+(!con.isClosed() ? "true" : "false" ));
            ps = con.prepareStatement("select customername from customer order by customername desc");
            rs = ps.executeQuery();
            out.print("<table>");
            out.print("<tr><th>CUSTOMER-NAME</th></tr>");
            while(rs.next()){
                out.print("<tr><td>"+rs.getString("customername")+"</td></tr>");
            }
            out.print("</table>");
        }catch(Exception e){
            throw e;
        }finally{
            if(con != null && !con.isClosed()){
                con.close();
            }
        }
    }
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out = null;
        try{
            res.setContentType("text/html");
            out = res.getWriter();
            out.println("<h2>Hi the testing is sucessfull !!!!!!</h2>");
            out.println("<h2>Hi the testing is sucessfull !!!!!!</h2>");
            out.println("<h2>Hi the testing is sucessfull !!!!!!</h2>");
            
            testDBConnection(out);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
}
