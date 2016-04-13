package com.epam.msfrolov.freewms.model;

import java.time.LocalDate;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class Individual extends NamedEntity {
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        checkNotNull(contact);
        this.contact = contact;
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
