package cl.reyeslopezdev.demo_noaris.persistence.repositories;

import cl.reyeslopezdev.demo_noaris.persistence.entities.phone.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
