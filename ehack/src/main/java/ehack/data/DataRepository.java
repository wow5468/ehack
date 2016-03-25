package ehack.data;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<DataEntity,Integer>{


	@Query("select *from data")
	public Map<String,Object> findByAllData();
}
