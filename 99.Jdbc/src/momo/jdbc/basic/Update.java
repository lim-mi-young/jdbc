package momo.jdbc.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {


	public static void main(String[] args) throws ClassNotFoundException {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "miyoung";
		String password = "a1234";
			
		Connection connection = null;		
		PreparedStatement pStatement = null;
				
		try {
			
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			
			pStatement = connection.prepareStatement("UPDATE memo SET content = ? WHERE id = ?");

        	pStatement.setString(1, "Hi!!");
        	pStatement.setLong(2, 1);
			
			pStatement.executeUpdate();
							
			pStatement.close();
			connection.close();
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
