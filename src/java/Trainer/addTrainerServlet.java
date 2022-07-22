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
 * @author User
 */
public class addTrainerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List errorMsgs = new LinkedList();
        
         try {

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dob = request.getParameter("dob");
            int phonenum = Integer.parseInt(request.getParameter("phoneNum"));
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            int shiftId = Integer.parseInt(request.getParameter("shift"));
            
            TrainerBean trainer = new TrainerBean(firstname, lastname, dob, phonenum, gender, email,shiftId);
            
            Connection conn = null;
            try {

                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("SELECT max(trainerid)+1 from TRAINER");
                ResultSet res = pstmt.executeQuery();
                int id = 0;
                while (res.next()) {
                    id = res.getInt(1);
                }
                if (id == 0) {
                    id = 1;
                }

                pstmt = conn.prepareStatement("INSERT INTO TRAINER (TRAINERID, FIRSTNAME, LASTNAME, DOB, PHONENO, GENDER, EMAIL, SHIFTID ) VALUES (?,?,?,?,?,?,?,?)");

                pstmt.setInt(1, id);
                pstmt.setString(2, firstname);
                pstmt.setString(3, lastname);
                pstmt.setString(4, dob);
                pstmt.setInt(5, phonenum);
                pstmt.setString(6, gender);
                pstmt.setString(7, email);
                pstmt.setInt(8, shiftId);
                pstmt.executeUpdate();

                
                String message = "You have successfully Added Trainer!";
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
