package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.Bank;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
@Transactional
public class BankDaoImpl implements BankDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Collection<Bank> getAll() {
        TypedQuery<Bank> query= sessionFactory.getCurrentSession()
                .createQuery("from Bank");
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Bank getBankById(long bankId) {
        //получение банка по id
        return sessionFactory.getCurrentSession()
                .load(Bank.class, bankId);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void saveBank(Bank bank) {
        sessionFactory.getCurrentSession().merge(bank);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void editBank(Bank bank) {
        sessionFactory.getCurrentSession()
                .update(bank);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deleteBank(Bank bank) {
        sessionFactory.getCurrentSession()
                  .delete(bank);
    }

//    @Override
//    public Collection<Client> getAllClients() {
//        TypedQuery<Client> query =  sessionFactory.getCurrentSession().
//                createQuery("SELECT c FROM Bank e JOIN e.clients c", Client.class);
//
//        return query.getResultList();
//    }
//
//    @Override
//    public Collection<Worker> getAllWorkers() {
//        return null;
//    }
}


/*
Root представляет собой выражение, описывающее область определения
некоторого множесва сохраняемых объектов Выражение Root<T> фактически означает,
что данный запрос должен выполняться над всеми объектами типа T,
тем самым напоминая выражение FROM в запросах SQL и JPQL.
Обратите внимание, что выражение Root<Person> (как и любое выражение в запросе) является типизированным,
причем параметр определяет тип значения выражения. Таким образом, тип Root<Person> определяет выражение,
результатом вычисления которого будет экземпляр типа Person.class
Root<T> – представляет собой аналог блока FROM
 */