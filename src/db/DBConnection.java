package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	// Constructor for the class, tries getting a connection to the
	// database and then saves it as a singleton
	private DBConnection() {
		String connectionString = "jdbc:sqlserver://hildur.ucn.dk:1433;databaseName=DMA-CSD-S211_10407494;user=DMA-CSD-S211_10407494;password=Password1!;encrypt=false";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(
					"Could not connect to database DMA-CSD-S211_10407494 @ hildur.ucn.dk:1433 as user DMA-CSD-S211_10407494 using password ******");
			System.out.println(
					"Connection string was: " + connectionString.substring(0, connectionString.length() - 10) + "....");
			e.printStackTrace();
		}

	}

	// The get instance for generating the singleton, called by
	// other classes
	public static DBConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	// Called after getInstance to get the connection in other
	// classes
	public Connection getConnection() {
		return connection;
	}

	// Called at the start of a new transaction to stop the
	// program from autocommitting to the database
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	// Called when the data is ready to be sent to the database,
	// commits the data and sets autoCommit to true
	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}

	// Called when a transaction needs to be cancelled or
	// a mistake was made
	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}

	// Called at the end of a transaction to close the
	// connection and free up resources
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeInsertWithIdentity(PreparedStatement ps) throws DataAccessException {
		// requires perpared statement to be created with the additional argument
		// PreparedStatement.RETURN_GENERATED_KEYS
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(0);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(e, "Could not execute insert");
		}
		return res;
	}
}
