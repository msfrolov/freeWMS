package com.epam.msfrolov.freewms.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public abstract class Document extends BaseEntity {
    private LocalDateTime date;
    private List<DocumentTableLine> table;
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

    public boolean contains(Object o) {
        return table.contains(o);
    }

    public Iterator<DocumentTableLine> iterator() {
        return table.iterator();
    }

    public <T> T[] toArray(T[] ts) {
        return table.toArray(ts);
    }

    public boolean add(DocumentTableLine documentTableLine) {
        checkNotNull(documentTableLine);
        return table.add(documentTableLine);
    }

    public boolean remove(Object o) {
        checkNotNull(o);
        return table.remove(o);
    }

    public boolean containsAll(Collection<?> collection) {
        checkNotNull(collection);
        return table.containsAll(collection);
    }

    public boolean addAll(Collection<? extends DocumentTableLine> collection) {
        checkNotNull(collection);
        return table.addAll(collection);
    }

    public boolean removeAll(Collection<?> collection) {
        checkNotNull(collection);
        return table.removeAll(collection);
    }

    public void clear() {
        table.clear();
    }

    public void sort(Comparator<? super DocumentTableLine> comparator) {
        table.sort(comparator);
    }

    public DocumentTableLine get(int i) {
        return table.get(i);
    }

    public DocumentTableLine set(int i, DocumentTableLine documentTableLine) {
        checkNotNull(documentTableLine);
        return table.set(i, documentTableLine);
    }

    public DocumentTableLine remove(int i) {
        return table.remove(i);
    }

    public int indexOf(Object o) {
        checkNotNull(o);
        return table.indexOf(o);
    }
}
