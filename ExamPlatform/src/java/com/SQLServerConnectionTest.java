package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
//        String username = "THE_NOOB_BANANA\\Toby Femi";
//        String password = "";
    try{
        try(Connection con = DriverManager.getConnection(url)){
            System.out.println("Connection Established");
        }
    }catch(SQLException e){
        System.out.println("Error");
        e.printStackTrace();
    }
    }
}