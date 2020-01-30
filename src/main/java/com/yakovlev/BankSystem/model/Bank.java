package com.yakovlev.BankSystem.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bank")
public class Bank extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 72380901026782819L;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<Worker> workers;

    public Bank() {
    }

    public Bank(String name, Set<User> users, Set<Worker> workers) {
        this.name = name;
        this.users = users;
        this.workers = workers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", workers=" + workers +
                '}';
    }
}
