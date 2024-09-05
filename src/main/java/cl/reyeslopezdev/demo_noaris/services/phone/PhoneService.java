package cl.reyeslopezdev.demo_noaris.services.phone;

import cl.reyeslopezdev.demo_noaris.persistence.entities.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<Phone> findAll();

    Optional<Phone> findById(Long id);

    Phone save(Phone phone);

    Optional<Phone> delete(Phone phone);
}
