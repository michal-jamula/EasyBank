package SpringSecurityUdemy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @JsonIgnore
    private String pwd;
    private String role;
    @Column(name = "create_dt")
    private String createDt;

    //JsonIgnore will not send this value to the front end, so the users will not know what authorities they have
    //Fetch type EAGER will load the authority values when the customer is being loaded.
    @JsonIgnore
    @OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
    private Set<Authority> authorities;
}
