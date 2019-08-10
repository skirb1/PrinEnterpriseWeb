package com.kirby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DbConnection {
	private final static String url="jdbc:mysql://web7.jhuep.com:3306/";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String user = "johncolter";
	private final static String password = "LetMeIn!";
	private final static String db = "class";
	public final static String options = "?serverTimezone=UTC";
	
	public DbConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<RecordBean> getRecords(String startDate) {
		ArrayList<RecordBean> recordList = new ArrayList<RecordBean>();
		
		try {
			Connection conn = DriverManager.getConnection(url + db + options, user, password);
			Statement statement = conn.createStatement();
			
			String query = "SELECT * FROM reservation";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				RecordBean record = new RecordBean();
				record.setFirstName(rs.getString("First"));
				record.setLastName(rs.getString("Last"));
				
				recordList.add(record);
			}
			
			rs.close();
			conn.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordList;
	}


}
