package kirby_mod11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {
	private final static String url="jdbc:mysql://web7.jhuep.com:3306/";
	private final static String driver = "com.myql.jdbc.Driver";
	private final static String user = "johncolter";
	private final static String password = "LetMeIn!";
	private final static String db = "class";
	public final static String options = "?serverTimezone=UTC";
	

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection(url + db + options, user, password);
			Statement statement = conn.createStatement();
			
			String query = "SELECT * FROM reservation";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				System.out.print("id: " + rs.getInt("idreservation"));
				System.out.print(" first: " + rs.getString("First"));
				
				System.out.println("");
			}
			
			rs.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
