package com.se3070_we_43.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class DBConnectionUtil.
 * 
 */
public class DBConnectionUtil extends CommonUtil {

	/** The connection. */
	private static Connection connection;
	
	/**
	 * Instantiates a new DB connection util.
	 */
	private DBConnectionUtil() {
	}

	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 */
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		/*
		 * This create new connection objects when connection is closed or it is null
		 */
		if (connection == null || connection.isClosed()) {

			Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));
			connection = DriverManager.getConnection(properties.getProperty(CommonConstants.URL),
					properties.getProperty(CommonConstants.USERNAME), properties.getProperty(CommonConstants.PASSWORD));
		}
		return connection;
	}
}