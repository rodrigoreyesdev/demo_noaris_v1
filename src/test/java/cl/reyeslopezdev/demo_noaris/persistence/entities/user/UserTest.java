package cl.reyeslopezdev.demo_noaris.persistence.entities.user;

import cl.reyeslopezdev.demo_noaris.persistence.entities.phone.Phone;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void giveUserThenValideteFields(){
        User user = new User();
        user.setName("Juan Rodriguez");
        user.setEmail("juan.rodriguez@ey.com");
        user.setPassword("User2024");
        user.setPhones(List.of(new Phone()));

        assertEquals("Juan Rodriguez", user.getName());
        assertEquals("juan.rodriguez@ey.com", user.getEmail());
        assertEquals("User2024", user.getPassword());
        assertEquals(1, user.getPhones().size());
    }
}