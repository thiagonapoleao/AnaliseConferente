
package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Lucas Vieira
 * 
 **/
public class Banco {
    public Connection con;
    public Statement stmt;
    public ResultSet rs;
    
    //String url = "jdbc:mysql://10.16.0.185:8899/eerg";
    String url = "jdbc:mysql://10.16.0.80:3306/tratativa";
    String user = "default";
    String password = "123456";
    String driver = "com.mysql.jdbc.Driver";
    
    public void openConnectionMysql() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, user, password);
            
            stmt = con.createStatement();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String sq) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

