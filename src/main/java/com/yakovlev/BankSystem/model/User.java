package com.yakovlev.BankSystem.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@DynamicUpdate
public class User extends AbstractModel implements Serializable, UserDetails {

    private static final long serialVersionUID = -3214347199438499160L;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "tin")
    private long tin;

    @Column(name="email")
    private String email;

    @Column(name="citizenship")
    private String citizenship;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name = "password")
    private String  password;

    @Transient
    transient private String confirmPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> result = new ArrayList();

        for(UserRole r : userRoles) {
            result.add(new SimpleGrantedAuthority(r.getListRole().name()));
        }
        return result;
    }

    @Override
    public String getUsername() {
            return String.valueOf(super.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "UserRole_id"))
    private Set<UserRole> userRoles;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankAccount> bankAccounts;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Passport passport;

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    public User() {
        super();
    }

    public User(long id) {
        super(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public long getTin() {
        return tin;
    }

    public String getEmail() {
        return email;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public Bank getBank() {
        return bank;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setTin(long tin) {
        this.tin = tin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
