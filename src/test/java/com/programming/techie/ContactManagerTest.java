package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager cm;

    @BeforeAll
    public void beforeAll() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each");
        cm = new ContactManager();
    }

    @Test
    public void shouldCreateContact() {

        cm.addContact("RAJ", "KUMAR", "0123123132");

        Assertions.assertFalse(cm.getAllContacts().isEmpty());

        Assertions.assertEquals(1, cm.getAllContacts().size());

        Assertions.assertTrue(cm.getAllContacts().stream().filter(contact -> contact.getFirstName().equals("RAJ") &&
                contact.getLastName().equals("KUMAR") && contact.getPhoneNumber().equals("0123123132")).findAny().isPresent());

    }

    @Test
    @DisplayName("FirstName is null")
    public void throwNullPointerExceptionWhenFirstNameisNull() {
        Assertions.assertThrows(RuntimeException.class, () -> cm.addContact(null, "KUMAR", "0123123132"));
    }

    @Test
    @DisplayName("LastName is null")
    public void throwNullPointerExceptionWhenLastNameisNull() {
        Assertions.assertThrows(RuntimeException.class, () -> cm.addContact("RAJ", "asd", "231231231"));
    }

    @Test
    @DisplayName("Assume true")
    public void assumeTrue() {
        System.setProperty("env", "DEV");
        Assumptions.assumeTrue("DEV".equalsIgnoreCase(System.getProperty("env")));
    }

    @RepeatedTest(3)
    @DisplayName("Assume true {currentRepetition} ")
    public void assumeFalse() {
        System.setProperty("env", "DEV1");
        Assumptions.assumeFalse("DEV".equalsIgnoreCase(System.getProperty("env")));
    }

    @ParameterizedTest
    @ValueSource(strings= {"0123123123","0123123123","23123123"})
    public void parameterizedTest(String value){
        cm.addContact("RAJ", "KUMAR", value);
        Assertions.assertFalse(cm.getAllContacts().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("mymethod")
    public void methodSourceParamertizedTest(){

    }

    private List<String> mymethod() {
        return Arrays.asList("0123123123","0123123123","23123123");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void csvSourceParamertizedTest(String phoneNumber){
        cm.addContact("RAJ", "KUMAR", phoneNumber);
        Assertions.assertFalse(cm.getAllContacts().isEmpty());
    }
}