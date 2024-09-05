package cl.reyeslopezdev.demo_noaris.persistence.entities.phone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Test
    void givenUserThenValidateFields() {
        Phone phone = new Phone();
        phone.setCityCode("1");
        phone.setNumber("123456");
        phone.setCountryCode("56");

        assertEquals("1", phone.getCityCode());
        assertEquals("123456", phone.getNumber());
        assertEquals("56", phone.getCountryCode());
        assertNotEquals("65", phone.getCityCode());
    }
}