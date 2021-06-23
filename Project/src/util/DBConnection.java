package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	/*private final String DB_URL = "jdbc:mysql://localhost/javaproject?" +
			"useUnicode = true&characterEncoding=utf8&serverTimezone=UTC";
	
	private final String USER_NAME = "root";
	private final String PASSWORD = "1234";*/
	private final String DB_URL = "jdbc:mysql://mirimmarket-db.ct8u0xyepbpg.us-east-1.rds.amazonaws.com/mirimmarket?" +
			"useUnicode = true&characterEncoding=utf8&serverTimezone=UTC";
	
	private final String USER_NAME = "admin";
	private final String PASSWORD = "mirim2003";
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	public DBConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			stmt = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public void insertDB(String name, String score, int stage) {
		String SQL = "insert into user (name, score, stage) values ('"+ name + "', '" + score + "','" + stage + "');"; 
		try {
			stmt.executeUpdate(SQL);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}