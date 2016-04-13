package com.epam.msfrolov.freewms.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public abstract class Document extends BaseEntity {
    private LocalDateTime date;
    private List<TableLine> table;
    private String comment;

    @Override
    public String toString() {
        return super.toString() +
                "date=" + date +
                ", tableSize=" + table.size() +
                ", comment='" + comment + '\'';
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        checkNotNull(date);
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        checkNotNull(comment);
        this.comment = comment;
    }

    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public Iterator<TableLine> iterator() {
        return table.iterator();
    }

    public boolean add(TableLine tableLine) {
        checkNotNull(tableLine);
        return table.add(tableLine);
    }

    public boolean containsAll(Collection<?> collection) {
        checkNotNull(collection);
        return table.containsAll(collection);
    }

    public boolean addAll(Collection<? extends TableLine> collection) {
        checkNotNull(collection);
        return table.addAll(collection);
    }

    public void clear() {
        table.clear();
    }

    public void sort(Comparator<? super TableLine> comparator) {
        table.sort(comparator);
    }

    public TableLine get(int i) {
        return table.get(i);
    }

    public TableLine set(int i, TableLine tableLine) {
        checkNotNull(tableLine);
        return table.set(i, tableLine);
    }

    public TableLine remove(int i) {
        return table.remove(i);
    }

}
