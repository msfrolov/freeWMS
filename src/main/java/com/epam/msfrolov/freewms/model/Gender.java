package com.epam.msfrolov.freewms.model;

import com.epam.msfrolov.freewms.util.Common;

public class Gender extends NamedEntity {

    public static final Gender UNKNOWN = new Gender(1, "unknown");
    public static final Gender MALE = new Gender(2, "male");
    public static final Gender FEMALE = new Gender(3, "female");

    public Gender() {
    }

    public Gender(Integer id, String name) {
        Common.checkNotNull(id);
        Common.checkNotNull(name);
        this.setId(id);
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Gender{" +
                super.toString() +
                "}";
    }


}
