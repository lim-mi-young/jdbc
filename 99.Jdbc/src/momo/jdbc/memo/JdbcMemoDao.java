package momo.jdbc.memo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMemoDao implements MemoDao {

    // 메모 입력 메소드
	@Override
    public boolean insert(Memo memo) {
		boolean result = false;
		
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("INSERT INTO memo (content) VALUES (?)")) {
            
        	pStatement.setString(1, memo.getContent());
            
            int affectedRows = pStatement.executeUpdate();
            
            if(affectedRows > 0) {
            	result = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 모든 메모 조회 메소드
	@Override
    public List<Memo> findAll() {
        List<Memo> memos = new ArrayList<>();
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("SELECT * FROM memo ORDER BY id DESC")) {
            
        	ResultSet rs = pStatement.executeQuery();
        	
            while (rs.next()) {  
                Long id = rs.getLong("id");
                String content = rs.getString("content");
                
                Memo memo = new Memo(id, content);
                memos.add(memo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memos;
    }

    // id로 메모 조회 메소드
	@Override
    public Memo findById(long id) {
    	Memo memo = null;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("SELECT * FROM memo WHERE id = ?")) {
        	
        	pStatement.setLong(1, id);
            
        	ResultSet rs = pStatement.executeQuery()
        			;
            if (rs.next()) {            	
                String content = rs.getString("content");
                memo = new Memo(id, content);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }    
    
    // 메모 수정 메소드
	@Override
    public boolean update(Memo memo) {
		boolean result = false;
		
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("UPDATE memo SET content = ? WHERE id = ?")) {
        	
        	pStatement.setString(1, memo.getContent());
        	pStatement.setLong(2, memo.getId());
        	
            int affectedRows = pStatement.executeUpdate();
            
            if(affectedRows > 0) {
            	result = true;
            }
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    // 메모 삭제 메소드
	@Override
    public boolean deleteById(long id) {
		boolean result = false;
		
        try (Connection conn = DataSource.getDataSource();
            PreparedStatement pStatement 
            	= conn.prepareStatement("DELETE FROM memo WHERE id = ?")) {
        	pStatement.setLong(1, id);
            int affectedRows = pStatement.executeUpdate();
            
            if(affectedRows > 0) {
            	result = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return result;
    }
}
