/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shift;

import Client.ClientBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class updateShiftServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List errorMsgs = new LinkedList();
         try {
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String fromtime = request.getParameter("fromtime");
            String totime = request.getParameter("totime");
            String desc = request.getParameter("desc");
            
            ShiftBean shift = new ShiftBean(title, fromtime, totime, desc);

            try {
                String driver = "org.apache.derby.jdbc.ClientDriver";
                String connectionString = "jdbc:derby://localhost:1527/GymDb;create=true;user=app;password=app";

                //Load the driver
                Class.forName(driver);

                //connect to the sample database
                Connection conn = DriverManager.getConnection(connectionString);

                PreparedStatement pstmt = conn.prepareStatement("UPDATE SHIFT SET SHIFTTITLE=?, SHIFTFROMTIME=?, SHIFTTOTIME=?, SHIFTDESC=? WHERE SHIFTID=" +id);

                pstmt.setString(1, title);
                pstmt.setString(2, fromtime);
                pstmt.setString(3, totime);
                pstmt.setString(4, desc);
                pstmt.executeUpdate();
                
                String message = "You have successfully Update Shift Information!";
                request.setAttribute("shift", shift);
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
