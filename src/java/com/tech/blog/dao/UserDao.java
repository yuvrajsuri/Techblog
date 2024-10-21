package com.tech.blog.dao;
import java.sql.*;

import com.tech.blog.entities.User;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public boolean SaveUser(User user)
    {
        boolean f= false;
        try{
            String query = "INSERT INTO users (name, email, password,gender,about) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());
            pstmt.executeUpdate(); 
            pstmt.close();
//            this.conn.close();
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }

    // get user by useremail and password

    // public User getUser(String  email, String password) {
    //     User user = null;

    //     try{
    //         String query = "SELECT * FROM users WHERE email = ? AND password = ?";
    //         PreparedStatement pstmt = this.conn.prepareStatement(query);
    //         pstmt.set(1,email);
    //         pstmt.set(2,password);

    //         ResultSet rs = pstmt.executeQuery();
    //         if(rs.next()){

    //         }
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }


}
