package com.epam.msfrolov.freewms.model;

public class Counterpart extends NamedEntity {
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
