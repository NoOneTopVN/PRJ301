/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiaht.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nghiaht.util.Util;

/**
 *
 * @author NghiaHuynh
 */
public class CategoryDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<CategoryDTO> getAllCategory() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        try {
            con = Util.makeConnection();
            String query = "SELECT [cid]\n"
                    + "      ,[cname]\n"
                    + "  FROM [dbo].[Category]";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getInt(1), rs.getString(2)));
            }
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
            return list;
        }
    }
    
//    public static void main(String[] args) throws SQLException {
//        CategoryDAO dao = new CategoryDAO();
//        List<CategoryDTO> list = dao.getAllCategory();
//        for (CategoryDTO c : list) {
//            System.out.println(c.toString());
//        }
//    }
}
