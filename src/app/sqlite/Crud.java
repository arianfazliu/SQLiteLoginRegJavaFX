/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.sqlite;

import app.config.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rashi
 */
public class Crud {

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + " id integer PRIMARY KEY,"
                + " name text NOT NULL,"
                + " email text NOT NULL,"
                + " password text NOT NULL"
                + ");";

        Connection connection = Connect.connect();
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void insert(String name, String email, String password) {
        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        Connection connection = Connect.connect();
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Connect.close();

    }

    public void update(String name, String email, String password) {
        String sql = "UPDATE users SET name = ? , " + "email = ?, " + "password = ? " + "WHERE id = ?";
        Connection connection = Connect.connect();
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, Config.currentUser);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Connect.close();

    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Connect.close();
    }

    public boolean select(String email, String password) {
        boolean status = false;
        String sql = "SELECT  * FROM users where email ='" + email + "' and password='" + password + "'";
        Connection connection = Connect.connect();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                status = true;
                Config.currentUser = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connect.close();

        return status;
    }
}
