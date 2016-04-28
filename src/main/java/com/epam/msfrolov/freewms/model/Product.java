package com.epam.msfrolov.freewms.model;

import java.util.Comparator;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class Product extends NamedEntity {
    public static final Comparator<Product> COMPARE_NAME
            = (o1, o2) -> o1.getName().compareTo(o2.getName());
    private ProductType type;
    private Measure measure;
    private String description;
    private String barcode;

    @Override
    public String toString() {
        return "Product{" +
                super.toString() +
                "type=" + type +
                ", measure=" + measure +
                ", description='" + description + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        checkNotNull(barcode);
        this.barcode = barcode;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        checkNotNull(type);
        this.type = type;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        checkNotNull(measure);
        this.measure = measure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
