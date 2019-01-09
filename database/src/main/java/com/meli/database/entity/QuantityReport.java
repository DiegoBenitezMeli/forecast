package com.meli.database.entity;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery(
        name="quantityReport",
        query="SELECT USER.* FROM USER_ AS USER WHERE ID = ?",
        resultClass=QuantityReport.class
)
public class QuantityReport {

    private String weather;

    private Integer quantity;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
