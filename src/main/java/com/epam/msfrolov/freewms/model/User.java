package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public class User extends NamedEntity {
    private UserRole role;
    private Individual individual;

    @Override
    public String toString() {
        return "User{" +
                super.toString() +
                "role=" + role +
                ", individual=" + individual +
                '}';
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        checkNotNull(role);
        this.role = role;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}
