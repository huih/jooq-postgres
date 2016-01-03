package com.ph.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this class is used to connect to database
 * 
 * @author Parviz
 * 
 */
public class JooqConnection {

	public static Connection getConnection()

	{

		Connection conn = null;

		String userName = "db_user";

		String password = "0000";

		String url = "jdbc:postgresql://192.168.199.164:5432/pgbench";

		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return conn;

	}

}
