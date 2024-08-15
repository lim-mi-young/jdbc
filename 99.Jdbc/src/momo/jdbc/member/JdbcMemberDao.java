package momo.jdbc.member;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;



public class JdbcMemberDao implements MemberDao {

    // 회원 가입 메소드
	@Override
    public boolean insert(Member member) {
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("INSERT INTO member (id, password, regdate) VALUES (?, ?, SYSDATE)")) {
            pStatement.setString(1, member.getId());
            pStatement.setString(2, member.getPassword());
            
            int affectedRows = pStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 모든 회원 조회 메소드
	@Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("SELECT * FROM member ORDER BY id DESC FETCH FIRST 5 ROWS ONLY")) {
            
        	ResultSet rs = pStatement.executeQuery();
        	
            while (rs.next()) {  
                String id = rs.getString("id");
                String password = rs.getString("password");
                Date  regdate = rs.getDate("regdate");
                Member member = new Member(id, password, regdate);
                members.add(member);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


    // 회원 ID로 회원 조회 메소드
	@Override
    public Member findById(String id) {
    	Member member = null;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("SELECT * FROM member WHERE id = ?")) {
        	
        	pStatement.setString(1, id);
            
        	ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                Date regdate = rs.getDate("regdate");
                member = new Member(id, password, regdate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }    
    
    // 회원 정보 수정 메소드
	@Override
    public boolean update(Member member) {
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement 
             	= conn.prepareStatement("UPDATE member SET password = ? WHERE id = ?")) {
        	pStatement.setString(1, member.getPassword());
        	pStatement.setString(2, member.getId());
            int affectedRows = pStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 탈퇴 메소드
	@Override
    public boolean deleteById(String id) {
        try (Connection conn = DataSource.getDataSource();
            PreparedStatement pStatement 
            	= conn.prepareStatement("DELETE FROM member WHERE id = ?")) {
        	pStatement.setString(1, id);
            int affectedRows = pStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/*	
    public boolean deleteMembers() {
        try (Connection conn = DataSource.getDataSource();
            PreparedStatement pStatement 
            	= conn.prepareStatement("DELETE FROM member")) {
            int affectedRows = pStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
*/	
	  public static void main(String[] args) { 
		  JdbcMemberDao dao = new JdbcMemberDao(); 
		  //System.out.println(dao.insert(new Member("1","1")));
		  //System.out.println(dao.insert(new Member("2","2")));
		  System.out.println(dao.findAll());
		  System.out.println(dao.findById("1"));
		  System.out.println(dao.update(new Member("1","10")));
		  System.out.println(dao.findAll());
		  System.out.println(dao.deleteById("1"));
		  System.out.println(dao.findAll()); 
		  //System.out.println(dao.insert(new Member("3","3")));
	  }
	 
}
