package com.epam.msfrolov.freewms.model;

import java.util.Comparator;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class TableLine extends BaseEntity {

    public static final Comparator<TableLine> COMPARE_COUNT
            = (o1, o2) -> o1.getCount().compareTo(o2.getCount());
    public static final Comparator<TableLine> COMPARE_COUNT_DESC
            = (o1, o2) -> o2.getCount().compareTo(o1.getCount());
    public static final Comparator<TableLine> COMPARE_PRODUCT_NAME
            = (o1, o2) -> o1.getProduct().getName().compareTo(o2.getProduct().getName());
    public static final Comparator<TableLine> COMPARE_PRODUCT_NAME_DESC
            = (o1, o2) -> o2.getProduct().getName().compareTo(o1.getProduct().getName());

    Product product;
    Integer count;

    @Override
    public String toString() {
        return "TableLine{" +
                super.toString() +
                "product=" + product +
                ", count=" + count +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        checkNotNull(product);
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        checkNotNull(count);
        this.count = count;
    }
}
