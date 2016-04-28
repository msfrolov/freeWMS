package com.epam.msfrolov.freewms.model;

import java.util.Comparator;

public class Counterpart extends NamedEntity {
    public static final Comparator<Counterpart> COMPARE_NAME
            = (o1, o2) -> o1.getName().compareTo(o2.getName());
    private Individual responsiblePerson;
    private String requisite;

    @Override
    public String toString() {
        return "Counterpart{" +
                super.toString() +
                "responsiblePerson=" + responsiblePerson +
                ", requisite=" + requisite +
                '}';
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public Individual getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Individual responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
