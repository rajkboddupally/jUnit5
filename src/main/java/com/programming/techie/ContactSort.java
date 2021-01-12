package com.programming.techie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactSort {
    public void sort() {

    }

    public static void main(String[] args) {
        Contact c1 = new Contact("RAJ", "BOD", "123");
        Contact c2 = new Contact("ADH", "BOD", "456");
        Contact c3 = new Contact("VISH", "BOD", "789");
        Contact c4 = new Contact("MEG", "BOD", "001");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);

        contacts.stream().forEach(contact -> System.out.println(contact.toString()));
        System.out.println("############ AFTER ##################");
        contacts.stream().sorted((x, y) -> y.getPhoneNumber().compareTo(x.getPhoneNumber())).forEach(c -> System.out.println(c.toString()));
        System.out.println("############ AFTER ##################");
        contacts = contacts.stream().sorted((x, y) -> y.getPhoneNumber().compareTo(x.getPhoneNumber())).collect(Collectors.toList());

        for (Contact contact : contacts)
            System.out.println(contact.toString());
    }
}
