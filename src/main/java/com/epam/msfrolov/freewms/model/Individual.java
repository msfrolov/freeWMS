package com.epam.msfrolov.freewms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class Individual extends NamedEntity {
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private List<String> contact = new ArrayList<>();

    @Override
    public String toString() {
        return "Individual{" +
                super.toString() +
                "lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", contact=" + contact +
                '}';
    }

    public Object[] toArrayContact() {
        return contact.toArray();
    }

    public Iterator<String> iteratorContact() {
        return contact.iterator();
    }

    public int size() {
        return contact.size();
    }

    public boolean isEmptyContact() {
        return contact.isEmpty();
    }

    public boolean containsContact(Object o) {
        checkNotNull(o);
        return contact.contains(o);
    }

    public boolean removeContact(Object o) {
        checkNotNull(o);
        return contact.remove(o);
    }

    public boolean addContact(String s) {
        checkNotNull(s);
        return contact.add(s);
    }

    public void clearContact() {
        contact.clear();
    }

    public void addContact(int i, String s) {
        checkNotNull(s);
        contact.add(i, s);
    }

    public String setContact(int i, String s) {
        checkNotNull(s);
        return contact.set(i, s);
    }

    public String getContact(int i) {
        return contact.get(i);
    }

    public String removeContact(int i) {
        return contact.remove(i);
    }

    public int indexOfContact(Object o) {
        checkNotNull(o);
        return contact.indexOf(o);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        checkNotNull(gender);
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
