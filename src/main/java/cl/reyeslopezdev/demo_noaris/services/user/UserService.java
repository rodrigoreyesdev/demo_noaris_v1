package cl.reyeslopezdev.demo_noaris.services.user;

import cl.reyeslopezdev.demo_noaris.persistence.entities.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(UUID id);

    User save(User user);

    Optional<User> delete(User user);

    User findUserByEmail(String email);
}
