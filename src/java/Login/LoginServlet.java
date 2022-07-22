/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author azree
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List errorMsgs = new LinkedList();

        try {
            String name = request.getParameter("username");
            String password = request.getParameter("password");

            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("/error.jsp");
                view.forward(request, response);
                return;
            }

            HttpSession session = request.getSession();

            LoginBean loginBean = new LoginBean(name, password);

            LoginDao loginDao = new LoginDao();

            String userValidate = loginDao.authenticateUser(loginBean);

            if (userValidate.equals("SUCCESS")) {
                session.setAttribute("name", name);
                request.getRequestDispatcher("/home.jsp").forward(request, response);
            } else {
                request.setAttribute("errMessage", userValidate);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
