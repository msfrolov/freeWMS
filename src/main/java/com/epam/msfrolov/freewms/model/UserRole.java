package com.epam.msfrolov.freewms.model;

import com.epam.msfrolov.freewms.util.Common;

public class UserRole extends NamedEntity {

    public static final UserRole ADMIN = new UserRole(1, "ADMIN");
    public static final UserRole STOCKMAN = new UserRole(2, "STOCKMAN");
    public static final UserRole ACCOUNTANT = new UserRole(3, "ACCOUNTANT");
    public static final UserRole USER = new UserRole(4, "USER");
    public static final UserRole GUEST = new UserRole(5, "GUEST");

    public UserRole() {
    }

    public UserRole(Integer id, String name) {
        Common.checkNotNull(id);
        Common.checkNotNull(name);
        this.setId(id);
        this.setName(name);
    }


    @Override
    public String toString() {
        return "UserRole{" +
                super.toString() +
                "}";
    }
}

