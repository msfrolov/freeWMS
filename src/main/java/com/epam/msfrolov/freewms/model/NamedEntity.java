package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public abstract class NamedEntity extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkNotNull(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() +
                "name='" + name + '\'';
    }
}
