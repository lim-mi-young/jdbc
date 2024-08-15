package momo.jdbc.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteById {

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
			
			pStatement = connection.prepareStatement("DELETE FROM memo WHERE id = ?");
			
			pStatement.setLong(1, 3);
			
			pStatement.executeUpdate();
							
			pStatement.close();
			connection.close();
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
