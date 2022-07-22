/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trainer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class deleteTrainerServlet extends HttpServlet {


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
        try {
            
            String id = request.getParameter("id");
            
            try {
                String driver = "org.apache.derby.jdbc.ClientDriver";
                String connectionString = "jdbc:derby://localhost:1527/GymDb;create=true;user=app;password=app";
                
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(connectionString);

                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM TRAINER WHERE TRAINERID=" + id);

                pstmt.executeUpdate();
                
                String message = "You have successfully delete Trainer!";
                request.setAttribute("message", message);
                RequestDispatcher view = request.getRequestDispatcher("/success.jsp");
                view.forward(request, response);

            } catch (Exception e) {
                RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
                view.forward(request, response);
            } 

        } catch (RuntimeException e) {
            RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
            view.forward(request, response);

        }
    }

}
