package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The main database client responsible for the communication with the database
 */
public class DatabaseClient {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public DatabaseClient(String url, String user, String password) {
		// Open db connection
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			;
		} catch (SQLException e) {
			System.err
					.println("Cannot connect to the database: " + e.toString());
		}
	}

	public void closeConnection() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
	}

}
