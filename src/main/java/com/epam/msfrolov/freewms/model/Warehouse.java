package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public class Warehouse extends NamedEntity {
    private Individual responsiblePerson;

    @Override
    public String toString() {
        return "Warehouse{" +
                super.toString() +
                "responsiblePerson=" + responsiblePerson +
                '}';
    }

    public Individual getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Individual responsiblePerson) {
        checkNotNull(responsiblePerson);
        this.responsiblePerson = responsiblePerson;
    }
}
