package com.example.WeddingPlannerApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_owners")
public class RestaurantOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long lId;

    @Column(name = "NAME")
    private String sName;

    @Column(name = "LAST_NAMES")
    private String sLastName;

    @Column(name = "EMAIL")
    private String sEmail;

    @Column(name = "PHONE")
    private Integer iPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Restaurant restaurant;

    public RestaurantOwner() {
    }

    public RestaurantOwner(Long lId, String sName, String sLastName, String sEmail,
                           Integer iPhone, Restaurant restaurant) {
        this.lId = lId;
        this.sName = sName;
        this.sLastName = sLastName;
        this.sEmail = sEmail;
        this.iPhone = iPhone;
        this.restaurant = restaurant;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public Integer getiPhone() {
        return iPhone;
    }

    public void setiPhone(Integer iPhone) {
        this.iPhone = iPhone;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "RestaurantOwner{" +
                "lId=" + lId +
                ", sName='" + sName + '\'' +
                ", sLastName='" + sLastName + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", iPhone=" + iPhone +
                '}';
    }
}
