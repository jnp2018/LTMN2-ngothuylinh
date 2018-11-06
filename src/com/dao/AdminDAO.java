package com.dao;

import com.model.Admin;
import com.util.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public boolean checkLogin(Admin admin){
        String sql = "SELECT COUNT(*) as count FROM admin where username = ? and password = ?";
        try {
            PreparedStatement st = ConnectDB.openConnect().prepareStatement(sql);
            st.setString(1, admin.getUsername());
            st.setString(2, admin.getPassword());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                if(rs.getInt("count") == 1){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Admin getAdmin(String username){
        String sql = "SELECT * FROM admin where username = ?";
        try {
            PreparedStatement st = ConnectDB.openConnect().prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Admin u = new Admin(rs.getString("username"), rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
