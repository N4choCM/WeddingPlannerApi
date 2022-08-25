package com.example.WeddingPlannerApi.entities;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long lId;

    @Column(name = "name")
    private String sName;

    @Column(name = "address")
    private String sAddress;

    @Column(name = "Vegan_menu")
    private boolean bVeganMenu;

    @Column(name = "Kids_menu")
    private boolean bKidsMenu;

    @Column(name = "Celiac_menu")
    private boolean bCeliacMenu;

    @Column(name = "Welcome_cocktel")
    private boolean bWelcomeCocktel;

    @Column(name = "Starters")
    private String sStarters;

    @Column(name = "Main_dish")
    private String sMainDish;

    @Column(name = "Dessert")
    private String sDessert;

    @Column(name = "Phone")
    private Integer iPhone;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wedding_id", nullable = false)
    private Wedding wedding;

    public Restaurant() {
    }

    public Restaurant(Long lId, String sName, String sAddress, boolean bVeganMenu, boolean bKidsMenu, boolean bCeliacMenu, boolean bWelcomeCocktel,
                      String sStarters, String sMainDish, String sDessert, Integer iPhone, Wedding wedding) {
        this.lId = lId;
        this.sName = sName;
        this.sAddress = sAddress;
        this.bVeganMenu = bVeganMenu;
        this.bKidsMenu = bKidsMenu;
        this.bCeliacMenu = bCeliacMenu;
        this.bWelcomeCocktel = bWelcomeCocktel;
        this.sStarters = sStarters;
        this.sMainDish = sMainDish;
        this.sDessert = sDessert;
        this.iPhone = iPhone;
        this.wedding = wedding;
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

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public boolean isbVeganMenu() {
        return bVeganMenu;
    }

    public void setbVeganMenu(boolean bVeganMenu) {
        this.bVeganMenu = bVeganMenu;
    }

    public boolean isbKidsMenu() {
        return bKidsMenu;
    }

    public void setbKidsMenu(boolean bKidsMenu) {
        this.bKidsMenu = bKidsMenu;
    }

    public boolean isbCeliacMenu() {
        return bCeliacMenu;
    }

    public void setbCeliacMenu(boolean bCeliacMenu) {
        this.bCeliacMenu = bCeliacMenu;
    }

    public boolean isbWelcomeCocktel() {
        return bWelcomeCocktel;
    }

    public void setbWelcomeCocktel(boolean bWelcomeCocktel) {
        this.bWelcomeCocktel = bWelcomeCocktel;
    }

    public String getsStarters() {
        return sStarters;
    }

    public void setsStarters(String sStarters) {
        this.sStarters = sStarters;
    }

    public String getsMainDish() {
        return sMainDish;
    }

    public void setsMainDish(String sMainDish) {
        this.sMainDish = sMainDish;
    }

    public String getsDessert() {
        return sDessert;
    }

    public void setsDessert(String sDessert) {
        this.sDessert = sDessert;
    }

    public Integer getiPhone() {
        return iPhone;
    }

    public void setiPhone(Integer iPhone) {
        this.iPhone = iPhone;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "lId=" + lId +
                ", sName='" + sName + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", bVeganMenu=" + bVeganMenu +
                ", bKidsMenu=" + bKidsMenu +
                ", bCeliacMenu=" + bCeliacMenu +
                ", bWelcomeCocktel=" + bWelcomeCocktel +
                ", sStarters='" + sStarters + '\'' +
                ", sMainDish='" + sMainDish + '\'' +
                ", sDessert='" + sDessert + '\'' +
                ", iPhone=" + iPhone +
                ", wedding=" + wedding +
                '}';
    }
}
