package momo.jdbc.member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//라이브러리 설정
//C:\app\IPG3\product\21c\dbhomeXE\jdbc\lib\ojdbc11.jar
public class DataSource {
	
	//ORACLE
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "miyoung";
    private static final String PASSWORD = "a1234";

//    MYSQL
//    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
//    private static final String USER = "miyoung";
//    private static final String PASSWORD = "a1234";
    
    public static Connection getDataSource() {
        // JDBC 드라이버 로드
		Connection connection = null;

		try {
			
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (SQLException e) {
			System.out.println("SQLException 발생");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 발생");
			//e.printStackTrace();
		}
		
		return connection;
    }
}
