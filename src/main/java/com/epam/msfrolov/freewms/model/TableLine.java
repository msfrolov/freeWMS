package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class TableLine extends BaseEntity {
    Integer lineNumber;
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
