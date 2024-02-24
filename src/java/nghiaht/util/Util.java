/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiaht.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NghiaHuynh
 */
public class Util {
    private static final String DB_NAME = "NghiaHT";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASSWORD = "12345";
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database=" + DB_NAME;
        con = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
        return con;
    }
}
