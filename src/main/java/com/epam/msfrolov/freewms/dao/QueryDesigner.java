package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.Document;
import com.epam.msfrolov.freewms.util.Common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class QueryDesigner {
    StringBuilder stringBuilder;


    public QueryDesigner() {
        stringBuilder = new StringBuilder();
    }

    public QueryDesigner comma() {
        stringBuilder.append(", ");
        return this;
    }

    public QueryDesigner space() {
        stringBuilder.append(" ");
        return this;
    }

    public QueryDesigner select() {
        stringBuilder.append(" SELECT ");
        return this;
    }

    public QueryDesigner from() {
        stringBuilder.append(" FROM ");
        return this;
    }

    public QueryDesigner left() {
        stringBuilder.append(" LEFT ");
        return this;
    }

    public QueryDesigner join() {
        stringBuilder.append(" JOIN ");
        return this;
    }

    public QueryDesigner right() {
        stringBuilder.append(" RIGHT ");
        return this;
    }

    public QueryDesigner text(String s) {
        stringBuilder.append(s);
        return this;
    }


    public QueryDesigner insertInto() {
        stringBuilder.append(" INSERT INTO ");
        return this;
    }

    public QueryDesigner where() {
        stringBuilder.append(" WHERE ");
        return this;
    }

    public QueryDesigner ob() {
        stringBuilder.append(" (");
        return this;
    }

    public QueryDesigner cb() {
        stringBuilder.append(") ");
        return this;
    }

    public QueryDesigner equal() {
        stringBuilder.append(" = ");
        return this;
    }

    public QueryDesigner update() {
        stringBuilder.append(" UPDATE ");
        return this;
    }

    public QueryDesigner set() {
        stringBuilder.append(" SET ");
        return this;
    }

    public QueryDesigner values() {
        stringBuilder.append(" VALUES ");
        return this;
    }

    public QueryDesigner rownum() {
        stringBuilder.append(" ROWNUM() ");
        return this;
    }

    public QueryDesigner orderBy() {
        stringBuilder.append(" ORDER BY ");
        return this;
    }

    public QueryDesigner and() {
        stringBuilder.append(" AND ");
        return this;
    }

    public QueryDesigner as() {
        stringBuilder.append(" AS ");
        return this;
    }

    public QueryDesigner lessThan() {
        stringBuilder.append(" < ");
        return this;
    }

    public QueryDesigner greaterThan() {
        stringBuilder.append(" > ");
        return this;
    }

    public QueryDesigner notEqual() {
        stringBuilder.append(" <> ");
        return this;
    }

    public QueryDesigner equalsOrLessThan() {
        stringBuilder.append(" <= ");
        return this;
    }

    public QueryDesigner equalsOrGreaterThan() {
        stringBuilder.append(" >= ");
        return this;
    }

    public QueryDesigner question() {
        stringBuilder.append(" ? ");
        return this;
    }

    public QueryDesigner id() {
        stringBuilder.append(" ID ");
        return this;
    }

    public QueryDesigner deletionMark() {
        stringBuilder.append(" DELETION_MARK ");
        return this;
    }

    public QueryDesigner asterisk() {
        stringBuilder.append(" * ");
        return this;
    }

    public QueryDesigner integer(int i) {
        stringBuilder.append(String.valueOf(i));
        return this;
    }

    public QueryDesigner string(String s) {
        stringBuilder.append('\'').append(s).append('\'');
        return this;
    }

    public QueryDesigner bool(boolean b) {
        stringBuilder.append(String.valueOf(b));
        return this;
    }

    public QueryDesigner date(LocalDate s) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        stringBuilder.append(s.format(formatter));
        return this;
    }

    public QueryDesigner dateTime(LocalDateTime s) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        stringBuilder.append(s.format(formatter));
        return this;
    }

    public QueryDesigner table(Object o) {
        checkNotNull(o);
        if (!(o instanceof Class)) {
            o = o.getClass();
        }
        return camelCaseToUpperCase(((Class) o).getSimpleName());
    }

    public QueryDesigner table(Object o, Document document) {
        checkNotNull(o);
        if (!(o instanceof Class)) {
            o = o.getClass();
        }
        String prefix = document.getClass().getSimpleName().replaceAll("Document", "");
        return camelCaseToUpperCase(prefix + ((Class) o).getSimpleName());
    }

    public QueryDesigner camelCaseToUpperCase(String s) {
        stringBuilder.append(Common.camelCaseToUpperCase(s));
        return this;
    }

    public QueryDesigner upperCaseToCamelCase(String s) {
        stringBuilder.append(Common.upperCaseToCamelCase(s));
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
