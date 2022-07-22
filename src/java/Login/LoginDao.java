/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author azree
 */
public class LoginDao {
    public String authenticateUser(LoginBean loginBean) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String usernameDB = "";
        String passwordDB = "";
        
        try{
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("Select login, password from Admin");
            while (resultSet.next())
            {
                usernameDB = resultSet.getString("login");
                passwordDB = resultSet.getString("password");
                
                if(username.equals(usernameDB) && password.equals(passwordDB)) {
                    return "SUCCESS";
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "Invalid user credentials";
    }
    
}
