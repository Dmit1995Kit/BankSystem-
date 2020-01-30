package com.yakovlev.BankSystem.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {

    private static final long serialVersionUID = -6588307216633965145L;

    @GenericGenerator(name = "generator" ,strategy = "foreign",
            parameters =  @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Transient
    private long clientId;

    @Column(name = "series")
    private int series;

    @Column(name = "num")
    private int num;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "issued_by")
    private String issuedBy;

    @Column(name = "place_of_registration")
    private String placeOfRegistration;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    public Passport() {
    }

    public Passport(long clientId, int series, int num, Date dateOfIssue, String issuedBy, String placeOfRegistration, User user) {
        this.clientId = clientId;
        this.series = series;
        this.num = num;
        this.dateOfIssue = dateOfIssue;
        this.issuedBy = issuedBy;
        this.placeOfRegistration = placeOfRegistration;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getPlaceOfRegistration() {
        return placeOfRegistration;
    }

    public void setPlaceOfRegistration(String placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
