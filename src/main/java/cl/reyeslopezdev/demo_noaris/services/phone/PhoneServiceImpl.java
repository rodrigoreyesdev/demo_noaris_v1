package cl.reyeslopezdev.demo_noaris.services.phone;

import cl.reyeslopezdev.demo_noaris.persistence.entities.phone.Phone;
import cl.reyeslopezdev.demo_noaris.persistence.repositories.PhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> findAll() {
        return (List<Phone>)  phoneRepository.findAll();
    }

    @Override
    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Transactional
    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Transactional
    @Override
    public Optional<Phone> delete(Phone phone) {
        //Validate
        Optional<Phone> phoneOptional = phoneRepository.findById(phone.getId());
        //Deleting if is Present
        phoneOptional.ifPresent(phoneDb ->
        {
            phoneRepository.delete(phoneDb);
        });
        //If is Present
        return phoneOptional;
    }
}
