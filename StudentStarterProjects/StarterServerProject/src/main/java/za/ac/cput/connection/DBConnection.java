 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**
 *
 * @author BURGERR
 */
public class DBConnection {
    public static Connection derbyConnection() throws SQLException
    {
        String DATABASE_URL = "jdbc:derby://localhost:1527/ProjectDB";
        String username = "app";
        String password = "app";

        Connection connection = DriverManager.getConnection(DATABASE_URL, username, password);
        return connection;
    }    
}
