/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rashi
 */
public class Connect {
    private static Connection connection = null;
    
    public static Connection connect() {
        String database = "jdbc:sqlite:users.db";
        try {
            connection = DriverManager.getConnection(database);
            System.err.println("Database Connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return connection;
        
    }
    
    public static void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) { 
                System.out.println(ex.getMessage());
            }
        }
    }
}
