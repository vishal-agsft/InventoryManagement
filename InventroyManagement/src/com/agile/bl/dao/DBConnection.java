package com.agile.bl.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Rupesh Bharuka
 *
 */
public class DBConnection {

	private static DBConnection instance;
	private static Connection connection;

	private DBConnection() {
		createConnection();
	}

	private void createConnection() {

		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://192.168.50.84:3306/InventorySystem";
//		final String DBURL = "jdbc:mysql://localhost:3306/InventorySystem";
		final String USERNAME = "root";
		final String PASSWORD = "root";

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return An object reference of type DBConnection
	 */
	public static DBConnection getInstance() {

		if (instance == null)
			synchronized (DBConnection.class) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		return instance;
	}
	
	/**
	 * @return An object reference of type Connection
	 */
	public Connection getConnection(){
		return connection;
	}

}// end of DBConnection class
