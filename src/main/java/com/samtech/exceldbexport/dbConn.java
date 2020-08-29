/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samtech.exceldbexport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Sammy
 */

public class dbConn {
    final static Logger logger = Logger.getLogger(dbConn.class);

    //Confirm Connection..
    /**public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //getPreparedStatement("SELECT * FROM USER_GROUP");
        String SelectSql = "SELECT USERNAME, GROUP_CODE FROM USERS";
        //String SelectSql = "SELECT * FROM DUAL";

        ResultSet resSet = getPreparedStatement(SelectSql).executeQuery();
        while (resSet.next()) {
            System.out.println(resSet.getString(1) + "  " + resSet.getString(2));
            //System.out.println(resSet.getString(1));
            //System.out.println(" I love Jesus ");
        }
    }*/
    
    /**public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
        PreparedStatement prepStatmt = null;
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@10.10.0.41:1521:CTSDB";
        String user = "ctsuser";
        String pass = "energycts";
        
        Connection Conn = DriverManager.getConnection(url,user,pass);
        prepStatmt = Conn.prepareStatement(sql);
        
        return prepStatmt;
    }*/
    
    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException, IOException{
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("/home/samuel/Projects/ExcelDBExport/db.properties");
            props.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
            logger.debug("=========== Error During File Reading ============" + ex);
        }
        
        String driver = props.getProperty("jdbc.driver");
        if (driver != null) {
            Class.forName(driver);
        }
        
        String url =      props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        PreparedStatement prepStatmt = null;        
        try {
            Connection Conn = DriverManager.getConnection(url, username, password);
            System.out.println("Yeah... It works!!!  url:"+url+ ", username:"+username+", password:"+password);
            logger.debug("Yeah... It works!!!  url:"+url+ ", username:"+username+", password:"+password);

            prepStatmt = Conn.prepareStatement(sql);
        } catch (Exception e) {
            System.err.println("=========== Error During Db Connection ============" + e);
            logger.debug("=========== Error During Db Connection ============" + e);
        }

        return prepStatmt;
    }
    
}  
