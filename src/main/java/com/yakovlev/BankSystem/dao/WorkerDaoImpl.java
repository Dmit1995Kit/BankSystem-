package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.Bank;
import com.yakovlev.BankSystem.model.Worker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;

public class WorkerDaoImpl implements WorkerDao {

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
    public Collection<Worker> getAll() {
        TypedQuery<Worker> query= sessionFactory.getCurrentSession()
                .createQuery("from Bank");
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Worker getWorkerById(long workerId) {
        return sessionFactory.getCurrentSession()
                .load(Worker.class, workerId);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void saveWorker(Worker worker) {
        sessionFactory.getCurrentSession().merge(worker);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void editWorker(Worker worker) {
        sessionFactory.getCurrentSession()
                .update(worker);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deleteWorker(Worker worker) {
        sessionFactory.getCurrentSession()
                .delete(worker);
    }
}
