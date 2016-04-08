package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class DocumentTableLine extends BaseEntity {
    Integer lineNumber;
    Product product;
    Integer count; //amount

    @Override
    public String toString() {
        return "DocumentTableLine{" +
                super.toString() +
                "lineNumber=" + lineNumber +
                ", product=" + product +
                ", count=" + count +
                '}';
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        checkNotNull(lineNumber);
        this.lineNumber = lineNumber;
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
