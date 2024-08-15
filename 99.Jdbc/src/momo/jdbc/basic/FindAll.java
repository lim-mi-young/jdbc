package momo.jdbc.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindAll {

	public static void main(String[] args) throws ClassNotFoundException {
		
		List<Memo> momos = new ArrayList<Memo>();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "miyoung";
		String password = "a1234";
			
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
				
		try {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			
			pStatement = connection.prepareStatement("SELECT * FROM memo ORDER BY id DESC");
			
			rs = pStatement.executeQuery();
			
			while (rs.next()) {
				Memo memo = new Memo();				

				memo.setId(rs.getLong("id"));
				memo.setContent(rs.getString("content"));
				
				momos.add(memo);
			}
			
			//db에서 읽어온 내용 출력하기
			for (Memo memo : momos) {
				System.out.println(memo);
			}
			

			pStatement.close();
			connection.close();
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
