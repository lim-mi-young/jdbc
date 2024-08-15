package momo.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//실행 속도 측정법 포함되어 있습니다.
public class Transaction {

	private static final String EMP_UPDATE_BY_DEPTNO_10_SQL = "UPDATE EMP SET sal = sal * 1.1 WHERE deptno = 10";
	private static final String EMP_UPDATE_BY_DEPTNO_20_SQL = "UPDATE EMP SET sal = sal * 1.2 WHERE deptno = 20";
	private static final String EMP_UPDATE_BY_DEPTNO_30_SQL = "UPDATE EMP SET sal = sal * 1.3 WHERE deptno = 30";
	
	public static void main(String[] args)  {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";

		Connection connection = null;
		PreparedStatement pStatement = null;

		try {
			
			//예외 발생 여부
			boolean exceptionYN = false;
						
	        Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

			// 이부분이 핵심
			connection.setAutoCommit(false);

			pStatement = connection.prepareStatement(Transaction.EMP_UPDATE_BY_DEPTNO_10_SQL);
			pStatement.executeUpdate();

			pStatement = connection.prepareStatement(Transaction.EMP_UPDATE_BY_DEPTNO_20_SQL);
			pStatement.executeUpdate();
			
			if(exceptionYN) {
				throw new Exception("강제 예외 발생!!!");
			}
			
			pStatement = connection.prepareStatement(Transaction.EMP_UPDATE_BY_DEPTNO_30_SQL);
			pStatement.executeUpdate();			
			
			connection.commit();
			System.out.println("commit 되었습니다.");				

			pStatement.close();
			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				connection.rollback();
				System.out.println("rollback 되었습니다.");
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
				System.out.println("rollback 되었습니다.");	
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}		
	}
}
