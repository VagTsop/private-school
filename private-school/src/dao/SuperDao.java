/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aggelos
 */
public class SuperDao {

	private final String URL = "jdbc:mysql://localhost:3306/private_school?serverTimezone=UTC";
	private final String USER = "";
	private final String PASS = "";

	private Connection conn;

	protected Connection getConn() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException ex) {
				Logger.getLogger(SuperDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return conn;
	}

}
