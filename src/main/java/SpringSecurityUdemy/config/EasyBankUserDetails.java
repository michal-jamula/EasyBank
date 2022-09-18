package SpringSecurityUdemy.config;

import SpringSecurityUdemy.model.Customer;
import SpringSecurityUdemy.model.SecurityCustomer;
import SpringSecurityUdemy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Here can implement UserDetailsManager to create more methods, including password change etc
@Service
public class EasyBankUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customer = new ArrayList<>();
        customer.add(customerRepository.findByEmail(username));
        if (customer.size() == 0) {
            throw new UsernameNotFoundException("User details not for for the following user: " + username);
        }
        return new SecurityCustomer(customer.get(0));
    }
}
