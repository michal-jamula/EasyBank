package SpringSecurityUdemy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
