package com.yakovlev.BankSystem.model;

import com.yakovlev.BankSystem.model.enums.ListRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 8912188949025990291L;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ListRole listRole;

    @ManyToMany(mappedBy = "userRoles", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Set<User> users = new HashSet();

    public UserRole() {
         super();
    }

    public UserRole(long id) {
        super(id);
    }

    public ListRole getListRole() {
        return listRole;
    }

    public void setListRole(ListRole listRole) {
        this.listRole = listRole;
    }
    public void addUser(User user) {
        this.users.add(user);
    }
}
