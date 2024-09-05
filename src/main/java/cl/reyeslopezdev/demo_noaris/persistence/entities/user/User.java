package cl.reyeslopezdev.demo_noaris.persistence.entities.user;

import cl.reyeslopezdev.demo_noaris.persistence.entities.phone.Phone;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Date created;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private UUID token;
    private Date lastLogin;
    private Date lastModified;
    @OneToMany(mappedBy = "phoneUser")
    private List<Phone> phones;

}
