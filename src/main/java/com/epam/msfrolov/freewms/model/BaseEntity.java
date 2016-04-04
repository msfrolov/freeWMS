package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public abstract class BaseEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        checkNotNull(id);
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "id'=" + id +'\'';
    }
}
