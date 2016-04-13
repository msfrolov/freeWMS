package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class TableLine extends BaseEntity {
    Product product;
    Integer amount;

    @Override
    public String toString() {
        return "TableLine{" +
                super.toString() +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        checkNotNull(product);
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        checkNotNull(amount);
        this.amount = amount;
    }
}
