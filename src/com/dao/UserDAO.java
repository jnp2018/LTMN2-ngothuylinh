package com.dao;

import com.model.User;
import com.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private ConnectDB connectdb = new ConnectDB();
    public UserDAO() {
    }
    public ArrayList<User> search(String text) {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM user where name LIKE ?";

        try {
            stmt = ConnectDB.openConnect().prepareStatement(sql);
            stmt.setString(1, "%"+text+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String sdt = rs.getString("sdt");
                User user = new User(id, name, department, sdt);
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("Please check getAllProduct index.jsp in UserDAO");
        }
        //connectdb.closeConnect();
        return list;
    }
    public boolean insertNew(User user) throws SQLException {
        String sql = "INSERT INTO `db_bai3`.`user` (`name`, `department`, `sdt`) VALUES (?, ?, ?);";
        stmt = ConnectDB.openConnect().prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getDepartment());
        stmt.setString(3, user.getSdt());
        return stmt.executeUpdate() > 0;
    }

    public boolean updateOld(User user) throws SQLException {
        String sql = "UPDATE `db_bai3`.`user` SET `name`=?, `department`=?, `sdt`=? WHERE `id`=?";
        stmt = ConnectDB.openConnect().prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getDepartment());
        stmt.setString(3, user.getSdt());
        stmt.setInt(4, user.getID());
        return stmt.executeUpdate() > 0;
    }
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM `db_bai3`.`user` WHERE `id`=?";
        stmt = ConnectDB.openConnect().prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    }

    public User getUserID(Integer id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id=?";
        stmt = ConnectDB.openConnect().prepareStatement(sql);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        User user = null;
        while (rs.next()) {
            Integer iduser = rs.getInt("id");
            String name = rs.getString("name");
            String department = rs.getString("department");
            String sdt = rs.getString("sdt");
            user = new User(iduser, name, department, sdt);
        }
        return user;
    }

}
