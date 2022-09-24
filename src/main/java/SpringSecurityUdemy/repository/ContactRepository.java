package SpringSecurityUdemy.repository;

import SpringSecurityUdemy.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//Added in section 6
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
