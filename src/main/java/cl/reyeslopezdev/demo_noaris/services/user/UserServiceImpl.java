package cl.reyeslopezdev.demo_noaris.services.user;

import cl.reyeslopezdev.demo_noaris.persistence.entities.user.User;
import cl.reyeslopezdev.demo_noaris.persistence.repositories.UserRepository;
import cl.reyeslopezdev.demo_noaris.services.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhoneService phoneService;

    private static final String EMAIL_REGEX = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";



    @Override
    public List<User> findAll() {
        return (List<User>)  userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        User userReturn = new User();
        if (this.validateUserData(user.getPassword(), user.getEmail())) {
            Date tsCreation = new Date();
            user.setCreated(tsCreation);
            user.setIsActive(true);
            user.setToken(UUID.randomUUID());
            user.setLastLogin(tsCreation);
            userRepository.save(user);
            user.getPhones().forEach(phone ->
                    {
                        phone.setTsCreation(tsCreation);
                        phone.setPhoneUser(user);
                        phoneService.save(phone);
                    }
            );
            userReturn = this.findUserByEmail(user.getEmail());
        }
        return userReturn;

    }

    @Override
    public Optional<User> delete(User user) {
        //Validate
        Optional<User> userOptional = userRepository.findById(user.getId());
        //Deleting if is Present
        userOptional.ifPresent(userDb ->
        {
            userRepository.delete(userDb);
        });
        //If is Present
        return userOptional;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validateUserData(String password, String email) {
        return validateExpressionPassword(password) && validateUserEmail(email);
    }

    public boolean validateExpressionPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean validateUserEmail(String email) {
        return validateExpressionEmail(email) && validateExistenceUserEmail(email);
    }

    public boolean validateExpressionEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean validateExistenceUserEmail(String email) {
        return email != null && userRepository.findByEmail(email) == null;
    }
}
