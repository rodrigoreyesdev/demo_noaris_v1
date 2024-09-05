package cl.reyeslopezdev.demo_noaris.persistence.entities.phone;

import cl.reyeslopezdev.demo_noaris.persistence.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date tsCreation;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User phoneUser;
    private String number;
    private String cityCode;
    private String CountryCode;

}
