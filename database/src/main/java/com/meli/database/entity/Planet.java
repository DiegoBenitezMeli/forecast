package com.meli.database.entity;

import javax.persistence.*;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    private String civilization;

    private Integer velocity;

    private String sence;

    @Column(name = "distance_from_sun")
    private Double distanceFromSun;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCivilization() {
        return civilization;
    }

    public void setCivilization(String civilization) {
        this.civilization = civilization;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public String getSence() {
        return sence;
    }

    public void setSence(String sence) {
        this.sence = sence;
    }

    public Double getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(Double distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }
}
