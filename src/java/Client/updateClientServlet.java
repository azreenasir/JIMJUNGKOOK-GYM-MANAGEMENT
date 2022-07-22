/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

/**
 *
 * @author azree
 */
public class updateClientServlet extends HttpServlet {

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List errorMsgs = new LinkedList();

        try {
            String id = request.getParameter("id");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            int phonenum = Integer.parseInt(request.getParameter("phoneNum"));
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            
            ClientBean client = new ClientBean(firstname, lastname, dob, phonenum, gender, address);

            Connection conn = null;
            try {
                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("UPDATE CLIENT SET FIRSTNAME=?, LASTNAME=?, DOB=?, PHONENO=?, GENDER=?, ADDRESS=? WHERE CLIENTID=" +id);

                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, dob);
                pstmt.setInt(4, phonenum);
                pstmt.setString(5, gender);
                pstmt.setString(6, address);
                pstmt.executeUpdate();
                
                String message = "You have successfully Update Client Information!";
                request.setAttribute("client", client);
                request.setAttribute("message", message);
                RequestDispatcher view = request.getRequestDispatcher("/success.jsp");
                view.forward(request, response);
                
                pstmt.close();
                conn.close();

            } catch (Exception e) {

                errorMsgs.add("An unexpected error Database: " + e.getMessage());
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
                view.forward(request, response);
            } 

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error:" + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
            view.forward(request, response);

        }
    }

}
