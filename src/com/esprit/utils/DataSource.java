/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.utils;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DataSource {
    
 
        private Connection cnx;

   
    
    private static DataSource instance;
    
    private final String URL = "jdbc:mysql://127.0.0.1/ecole";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
    
   // public static int chekLogin(String login, String password){
//       // Connection cnx = DBConnection.cnx;
//        if (cnx == null)) return -1;
//        String sql = "SELECT * FROM User WHERE login=? AND password?";
//        try{
//            PreparableStatement prest = cnx.prepareStatement(sql);
//            prest.setString(1, login);
//            prest.setString(2, password);
//            ResultSet rs = prest.executeQuery();
//            
//            while(rs.next()){
//                return 0;
//            }
//            return 1;
//            
//        }
//        catch (SQLExeception se){
//            se.printStackTrace();
//        }
//    }


