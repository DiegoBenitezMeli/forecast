package com.meli.database.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "forecast")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "day_number")
    private int dayNumber;

    @Column(name = "day_timestamp")
    private Timestamp dayTimestamp;

    private String weather;

    private Double perimeter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Timestamp getDayTimestamp() {
        return dayTimestamp;
    }

    public void setDayTimestamp(Timestamp dayTimestamp) {
        this.dayTimestamp = dayTimestamp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }
}
