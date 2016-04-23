package com.epam.msfrolov.freewms.model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public abstract class Document extends BaseEntity {

    private LocalDate date;
    private List<TableLine> table;
    private String comment;

    public Document() {
        table = new ArrayList<>();
        date = LocalDate.now();
    }

    @Override
    public String toString() {
        return super.toString() +
                "date=" + date +
                ", tableSize=" + table.size() +
                ", comment='" + comment + '\'';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public boolean remove(Object o) {
        return table.remove(o);
    }

    public void forEach(Consumer<? super TableLine> consumer) {
        table.forEach(consumer);
    }

    public Stream<TableLine> stream() {
        return table.stream();
    }

    public boolean removeIf(Predicate<? super TableLine> predicate) {
        return table.removeIf(predicate);
    }

    public Stream<TableLine> parallelStream() {
        return table.parallelStream();
    }

    public List<TableLine> getSubList(int i1, int i2) {
        return table.subList(i1, i2);
    }
}
