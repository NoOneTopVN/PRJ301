/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiaht.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nghiaht.category.CategoryDTO;
import nghiaht.util.Util;

/**
 *
 * @author NghiaHuynh
 */
public class AccountDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public AccountDTO login(String user, String pass) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "select * from Account where [user] = ? and pass = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, user);
            stm.setString(2, pass);
            rs = stm.executeQuery();

            while (rs.next()) {
                return new AccountDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public AccountDTO checkAccountExist(String user) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "select * from Account where [user] = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, user);
            rs = stm.executeQuery();

            while (rs.next()) {
                return new AccountDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void signup(String user, String pass) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "INSERT INTO [dbo].[Account]\n"
                    + "           ([user]\n"
                    + "           ,[pass]\n"
                    + "           ,[isSell]\n"
                    + "           ,[isAdmin])\n"
                    + "     VALUES\n"
                    + "           (?, ?, 0, 0)";
            stm = con.prepareStatement(query);
            stm.setString(1, user);
            stm.setString(2, pass);
            stm.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

//    public static void main(String[] args) throws SQLException {
//        AccountDAO dao = new AccountDAO();
//        AccountDTO a = dao.checkAccountExist("Adam");
//        if (a != null) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }
//    }
}
