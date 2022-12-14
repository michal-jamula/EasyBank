package SpringSecurityUdemy.repository;

import SpringSecurityUdemy.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Added in section 6
public interface LoanRepository extends CrudRepository<Loans, Long> {


	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
