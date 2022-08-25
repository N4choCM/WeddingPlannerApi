package com.example.WeddingPlannerApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "weddings")
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long lId;

    @Column(name = "Address")
    private String sAddress;

    @Column(name = "Christian")
    private boolean bChristian;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Wedding() {
    }

    public Wedding(Long lId, String sAddress, boolean bChristian, User user, Restaurant restaurant) {
        this.lId = lId;
        this.sAddress = sAddress;
        this.bChristian = bChristian;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Long getlId() {
        return lId;
    }

    public void setlId(Long lId) {
        this.lId = lId;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public boolean isbChristian() {
        return bChristian;
    }

    public void setbChristian(boolean bChristian) {
        this.bChristian = bChristian;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Wedding{" +
                "lId=" + lId +
                ", sAddress='" + sAddress + '\'' +
                ", bChristian=" + bChristian +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
