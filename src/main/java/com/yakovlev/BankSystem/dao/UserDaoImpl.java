package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.User;
import com.yakovlev.BankSystem.model.enums.ListRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Collection<User> getAll() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().
                createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getById(long userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public List getAllUnconfirmed() {
        return sessionFactory.getCurrentSession().
                createQuery("u.id, u.userName from User u left join u.userRoles r group by u.userName having count(r)=0").list();
    }

    @Override
    public List getAllConfirmed() {
        return sessionFactory.getCurrentSession().
                createQuery("select from User u join u.userRoles r where r.listRole=:role").
                setParameter("role", ListRole.ROLE_CLIENT).list();
    }

    @Override
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void save(User user) {
       sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void delete(User user) {
       sessionFactory.getCurrentSession().delete(user);
    }
}
