package SpringSecurityUdemy.controller;

import SpringSecurityUdemy.model.Customer;
import SpringSecurityUdemy.model.Loans;
import SpringSecurityUdemy.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

//    Difference between Pre and PostAuthorize: PreAuthorize will check before making a database transaction,
//    PostAuthorize will make the transaction, then authorize if the user is able to use that data ( useful to check if user has authority based on data)
//    For more complex authorization, use class PermissionEvaluator and hasPermission methods...
    @PostMapping("/myLoans")
    @PostAuthorize("hasRole('ROOT')")
    public List<Loans> getLoanDetails(@RequestBody Customer customer) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
        if (loans != null ) {
            return loans;
        }else {
            return null;
        }
    }

}
