package com.epam.msfrolov.freewms.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class Counterpart extends NamedEntity {
    private Individual responsiblePerson;
    private List<String> requisite = new ArrayList<>();

    @Override
    public String toString() {
        return "Counterpart{" +
                super.toString() +
                "responsiblePerson=" + responsiblePerson +
                ", requisite=" + requisite +
                '}';
    }

    public int sizeRequisite() {
        return requisite.size();
    }

    public boolean addRequisite(String s) {
        checkNotNull(s);
        return requisite.add(s);
    }

    public boolean removeRequisite(Object o) {
        checkNotNull(o);
        return requisite.remove(o);
    }

    public Object[] requisiteToArray() {
        return requisite.toArray();
    }

    public Iterator<String> iteratorRequisite() {
        return requisite.iterator();
    }

    public Individual getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Individual responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
