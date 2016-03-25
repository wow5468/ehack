package ehack.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<DataEntity,Integer>{
	public List<DataEntity> findByMuuid(String muuid);
}
