package momo.jdbc.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//라이브러리 설정
//C:\app\IPG3\product\21c\dbhomeXE\jdbc\lib\ojdbc11.jar

public class Insert {
	
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
			
			pStatement = connection.prepareStatement("INSERT INTO memo (content) VALUES (?)");
			
			pStatement.setString(1, "안녕하세요");
			
			pStatement.executeUpdate();
				
/*			true if the first result is a ResultSet object;
			false if the first result is an update count or there is no result*/
			
			pStatement.close();
			connection.close();
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
