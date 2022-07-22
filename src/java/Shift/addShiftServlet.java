/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shift;

import java.io.IOException;
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
public class addShiftServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         List errorMsgs = new LinkedList();
         try {

            String title = request.getParameter("shiftTitle");
            String fromTime = request.getParameter("shiftFromTime");
            String toTime = request.getParameter("shiftToTime");
            String desc = request.getParameter("shiftDesc");
            
            
            ShiftBean shift = new ShiftBean(title, fromTime, toTime, desc);
            
            Connection conn = null;
            try {

                conn = DBConnection.createConnection();

                PreparedStatement pstmt = conn.prepareStatement("SELECT max(shiftid)+1 from Shift");
                ResultSet res = pstmt.executeQuery();
                int id = 0;
                while (res.next()) {
                    id = res.getInt(1);
                }
                if (id == 0) {
                    id = 1;
                }

                pstmt = conn.prepareStatement("INSERT INTO SHIFT (SHIFTID, SHIFTTITLE, SHIFTFROMTIME, SHIFTTOTIME,SHIFTDESC ) VALUES (?,?,?,?,?)");

                pstmt.setInt(1, id);
                pstmt.setString(2, title);
                pstmt.setString(3, fromTime);
                pstmt.setString(4, toTime);
                pstmt.setString(5, desc);
                pstmt.executeUpdate();

                
                String message = "You have successfully Added Shift!";
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
