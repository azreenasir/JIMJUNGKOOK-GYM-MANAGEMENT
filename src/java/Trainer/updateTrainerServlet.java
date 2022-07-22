/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trainer;

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
public class updateTrainerServlet extends HttpServlet {
   

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            String email = request.getParameter("email");
            int shiftId = Integer.parseInt(request.getParameter("shift"));
            
            TrainerBean trainer = new TrainerBean(firstname, lastname, dob, phonenum, gender, email, shiftId);

            try {
                String driver = "org.apache.derby.jdbc.ClientDriver";
                String connectionString = "jdbc:derby://localhost:1527/GymDb;create=true;user=app;password=app";

                //Load the driver
                Class.forName(driver);

                //connect to the sample database
                Connection conn = DriverManager.getConnection(connectionString);

                PreparedStatement pstmt = conn.prepareStatement("UPDATE TRAINER SET FIRSTNAME=?, LASTNAME=?, DOB=?, PHONENO=?, GENDER=?, EMAIL=?, SHIFTID=? WHERE TRAINERID=" +id);

                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, dob);
                pstmt.setInt(4, phonenum);
                pstmt.setString(5, gender);
                pstmt.setString(6, email);
                pstmt.setInt(7, shiftId);
                pstmt.executeUpdate();
                
                String message = "You have successfully Update Trainer Information!";
                request.setAttribute("trainer", trainer);
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
