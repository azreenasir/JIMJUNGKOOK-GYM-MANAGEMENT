/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import Package.PackageBean;
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
 * @author azree
 */
public class addPaymentSevlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List errorMsgs = new LinkedList();

        try {

            
            double amount = Double.parseDouble(request.getParameter("pymtAmount"));
            String desc = request.getParameter("pymtDesc");
            String date = request.getParameter("pymtDate");
            int pckgid = Integer.parseInt(request.getParameter("package"));
            int clientid = Integer.parseInt(request.getParameter("client"));
            
            PaymentBean pymnt = new PaymentBean(amount, desc, date,pckgid,clientid);
            
            Connection conn = null;
            try {

                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("SELECT max(paymentid)+1 from Payment");
                ResultSet res = pstmt.executeQuery();
                int id = 0;
                while (res.next()) {
                    id = res.getInt(1);
                }
                if (id == 0) {
                    id = 1;
                }

                pstmt = conn.prepareStatement("INSERT INTO PAYMENT (PAYMENTID, PAYMENTAMOUNT, PAYMENTDESC, PAYMENTDATE, PACKAGEID, CLIENTID ) VALUES (?,?,?,?,?,?)");

                pstmt.setInt(1, id);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, desc);
                pstmt.setString(4, date);
                pstmt.setInt(5, pckgid);
                pstmt.setInt(6, clientid);
                pstmt.executeUpdate();

                
                String message = "You have successfully Make the payment!";
                request.setAttribute("pymnt", pymnt);
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
