/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiaht.product;

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
public class ProductDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public List<ProductDTO> getAllProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = Util.makeConnection();
            String query = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[image]\n"
                    + "      ,[price]\n"
                    + "      ,[title]\n"
                    + "      ,[description]\n"
                    + "      ,[cateID]\n"
                    + "      ,[sell_ID]\n"
                    + "  FROM [dbo].[product]";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6)));
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

    public ProductDTO getLast() throws SQLException, ClassNotFoundException {
        try {
            con = Util.makeConnection();
            String query = "SELECT TOP 1 * FROM product ORDER BY id DESC";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6));
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
        }

        return null;
    }

    public List<ProductDTO> getProductByCID(String cid) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = Util.makeConnection();
            String query = "select * from product where cateID = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6)));
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

    public ProductDTO getProductByID(String id) throws SQLException, ClassNotFoundException {
        try {
            con = Util.makeConnection();
            String query = "SELECT * FROM product WHERE ID = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6));
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
        }
        return null;
    }

    public List<ProductDTO> searchByName(String txtSearch) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = Util.makeConnection();
            String query = "select * from product where [name] like ?";
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + txtSearch + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6)));
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

    public List<ProductDTO> getProductBySellID(int sid) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        try {
            con = Util.makeConnection();
            String query = "select * from product where sell_ID = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6)));
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

    public void deleteProduct(String pid) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "delete from product where id = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, pid);
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

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "INSERT INTO [dbo].[product]\n"
                    + "           ([name]\n"
                    + "           ,[image]\n"
                    + "           ,[price]\n"
                    + "           ,[title]\n"
                    + "           ,[description]\n"
                    + "           ,[cateID]\n"
                    + "           ,[sell_ID])\n"
                    + "     VALUES\n"
                    + "           (?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, image);
            stm.setString(3, price);
            stm.setString(4, title);
            stm.setString(5, description);
            stm.setString(6, category);
            stm.setInt(7, sid);
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

    public void updateProduct(String id, String name, String image, String price,
            String title, String description, String category, int sid) throws SQLException {
        try {
            con = Util.makeConnection();
            String query = "UPDATE [dbo].[product]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[cateID] = ?\n"
                    + "      ,[sell_ID] = ?\n"
                    + " WHERE id = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, image);
            stm.setString(3, price);
            stm.setString(4, title);
            stm.setString(5, description);
            stm.setString(6, category);
            stm.setInt(7, sid);
            stm.setString(8, id);
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

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ProductDAO dao = new ProductDAO();
//        dao.updateProduct("33", "giay adidas", "1111", "200.0", "giay", "dep", "1", 1);
//
//    }
}
