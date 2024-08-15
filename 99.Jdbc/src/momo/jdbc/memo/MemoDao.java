package momo.jdbc.memo;
import java.util.List;

public interface MemoDao {

	boolean insert(Memo memo);
	List<Memo> findAll();

	Memo findById(long id);  
    boolean update(Memo memo);
    
    boolean deleteById(long id);

}
