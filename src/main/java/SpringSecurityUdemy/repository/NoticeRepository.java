package SpringSecurityUdemy.repository;

import SpringSecurityUdemy.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Added in section 6
public interface NoticeRepository extends CrudRepository<Notice, Long> {


	//Use "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt" after production
	@Query(value = "from Notice n where CURDATE() > noticBegDt")
	List<Notice> findAllActiveNotices();

}
