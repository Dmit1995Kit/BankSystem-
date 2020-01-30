package com.yakovlev.BankSystem.dao;
import com.yakovlev.BankSystem.model.Passport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
@Transactional
public class PassportDaoImpl implements PassportDao {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Collection<Passport> getAll() {
        TypedQuery<Passport> query = sessionFactory.getCurrentSession().
                createQuery("from Passport");

        return query.getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Passport getById(long passportId) {
        return sessionFactory.getCurrentSession().load(Passport.class, passportId);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void savePassport(Passport passport) {
        sessionFactory.getCurrentSession().merge(passport);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deletePassport(Passport passport) {
        sessionFactory.getCurrentSession().delete(passport);
    }
}
