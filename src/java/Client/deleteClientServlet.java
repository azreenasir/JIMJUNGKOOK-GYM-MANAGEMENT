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
public class deleteClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");

            Connection conn = null;
            try {

                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PAYMENT WHERE CLIENTID=" + id);
                PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM ATTENDANCE WHERE CLIENTID=" + id);
                PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM CLIENT WHERE CLIENTID=" + id);

                pstmt.executeUpdate();
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();

                String message = "You have successfully delete client!";
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
