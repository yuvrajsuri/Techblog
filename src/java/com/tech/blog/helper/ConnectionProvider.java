/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.blog.helper;

/**
 *
 * @author yuvraj.suri
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java .sql.*;

public class ConnectionProvider 
{
    private static Connection conn;

    public static Connection getConnection()
    {
        try{
            if(conn==null)
            {
               String url= "jdbc:postgresql://localhost:5432/techblog";
               String username = "postgres";
               String password = "Kipl1234";           //for office laptop

//                 String url= "jdbc:postgresql://localhost:5432/techblog";
//                 String username = "postgres";
//                 String password = "yuvi2002";             //for home laptop
                // driver class load
                Class.forName("org.postgresql.Driver");
                
                // create a connection
                conn = DriverManager.getConnection(url, username, password);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return conn;
    }
    // public static void main(String[] args)
    // {
    //     String url= "jdbc:postgresql://localhost:5432/techblog";
    //     String username = "postgres";
    //     String password = "Kipl1234";

    //     getConnection(url, username, password);
    // }
}

