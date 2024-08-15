package momo.jdbc.member;
import java.util.List;

public interface MemberDao {

	public boolean insert(Member member);
    public List<Member> findAll();
    
	public Member findById(String id);

    public boolean update(Member member);
    public boolean deleteById(String id);

}
