package ui;

import java.sql.SQLException;

import db.DBConnection;

public class TryMe {
	
	public static void main(String[] args) {
		
		//Tests out DBConnection and prints out ConnectionId
		System.out.println(DBConnection.getInstance().getConnection());
		
		//Tests the DB by creating and filling two tables
		try {
			testDB();
		} catch (SQLException e) {
			System.err.println("testDB failed");
			e.printStackTrace();
		}
		//Tests the disconnect from the DB
		DBConnection.getInstance().disconnect();
	}
	
	//Test method, drops tables, and builds new
	public static void testDB() throws SQLException {
		e("drop table persons");
		e("drop table groups");
		for(int i = 0 ; i < script.length; i++) {
			e(script[i]);
		}
	}

	//Calls the statements in the database
	private static void e(String sql) throws SQLException {
		DBConnection.getInstance().getConnection().createStatement().executeUpdate(sql);
	}
	
	//Contains the scripts testDB uses
	private static final String[] script = {
			"create table groups (id int primary key identity(1,1), name varchar(64), description varchar(255))",
			"create table persons (id int primary key identity(1,1),name varchar(50),email varchar(120),phone varchar(25),birth_date date,groups_id int not null foreign key references groups(id) on delete cascade on update cascade)",
			"insert into groups values('friends', 'all my buddies');",
			"insert into groups values('family', 'my dear beloved family');",
			"insert into persons values('Joe', 'joe@email.com', '12121212', '1995-05-05', 1);",
			"insert into persons values('Mom', 'mom@email.com', '1234567', '1965-05-05', 2);",
			"insert into persons values('Dad', 'dad@email.com', '1234567', '1963-03-03', 2);"
	};
	
}
