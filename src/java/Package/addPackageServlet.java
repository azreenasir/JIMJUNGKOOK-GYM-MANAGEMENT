/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

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
public class addPackageServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List errorMsgs = new LinkedList();

        try {

            String title = request.getParameter("pckgTitle");
            double fee = Double.parseDouble(request.getParameter("pckgFee"));
            String desc = request.getParameter("pckgDesc");
            
            PackageBean pckg = new PackageBean(title, fee, desc);
            
            Connection conn = null;
            try {

                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("SELECT max(packageid)+1 from Package");
                ResultSet res = pstmt.executeQuery();
                int id = 0;
                while (res.next()) {
                    id = res.getInt(1);
                }
                if (id == 0) {
                    id = 1;
                }

                pstmt = conn.prepareStatement("INSERT INTO PACKAGE (PACKAGEID, PACKAGETITLE, PACKAGEFEE, PACKAGEDESC ) VALUES (?,?,?,?)");

                pstmt.setInt(1, id);
                pstmt.setString(2, title);
                pstmt.setDouble(3, fee);
                pstmt.setString(4, desc);
                pstmt.executeUpdate();

                
                String message = "You have successfully Added Package!";
                request.setAttribute("pckg", pckg);
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
