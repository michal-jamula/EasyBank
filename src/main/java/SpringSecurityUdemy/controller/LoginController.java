package SpringSecurityUdemy.controller;

import SpringSecurityUdemy.model.Customer;
import SpringSecurityUdemy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Principal user) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(user.getName());

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return customer;
        }else {
            return null;
        }

    }

}
