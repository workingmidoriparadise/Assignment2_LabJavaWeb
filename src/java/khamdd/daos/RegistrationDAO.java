/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import khamdd.utils.DBUtilities;

/**
 *
 * @author KHAM
 */
public class RegistrationDAO {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    private static void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if(ps != null){
            ps.close();
        }
        if(conn != null){
            conn.close();
        }
    }
    
    public String login(String email, String password) throws Exception{
        String role = "";
        try{
            String sql = "Select role from tbl_user where email like ? and password like ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                role = rs.getString("role");
            }
        } finally{
            closeConnection();
        }
        return role;
    }
    
    public boolean createAccount(String email, String password, String name) throws Exception{
        boolean check = false;
        
        try{
            String sql = "Insert into tbl_user(email, password, name, role, status) values"
                    + "(?,?,?,?,?)";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, "student");
            ps.setString(5, "new");
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
