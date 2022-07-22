/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales;

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
 * @author azree
 */
public class salesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        List errorMsgs = new LinkedList();

        try {

            String datefrom = request.getParameter("dateFrom");
            String dateto = request.getParameter("dateTo");
            
            Connection conn = null;
            try {

                conn = DBConnection.createConnection();
                
                
                PreparedStatement pstmt = conn.prepareStatement(" SELECT COUNT(PAYMENTID), SUM(PAYMENTAMOUNT) AS TOTALPRICE, PAYMENTDATE FROM PAYMENT "
                        + " WHERE PAYMENTDATE BETWEEN '" + datefrom + "' AND '" + dateto + "' GROUP BY PAYMENTID, PAYMENTDATE ");
                ResultSet res = pstmt.executeQuery();
                double sum = 0;
                int count = 0;
                while (res.next()) {
                    sum = sum + res.getDouble(2);
                    count = count + res.getInt(1);
                }
                
                request.setAttribute("totalsales", sum);
                request.setAttribute("count", count);
                RequestDispatcher view = request.getRequestDispatcher("/salesReport.jsp");
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
