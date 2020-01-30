package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.BankAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
@Transactional
public class BankAccountDaoImpl implements BankAccountDao {

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
    public Collection<BankAccount> getAll() {
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<BankAccount> criteria = criteriaBuilder.createQuery(BankAccount.class);
//        Root<BankAccount> bankAccountRoot = criteria.from(BankAccount.class);
//
//        return (Collection<BankAccount>) criteria.select(bankAccountRoot);
        TypedQuery<BankAccount> query = sessionFactory.getCurrentSession().
                createQuery("from BankAccount");
        return query.getResultList();
    }

//    //обработка для ManyToOne
//    @Transactional(propagation = Propagation.MANDATORY)
//    @Override
//    public List<Client> getClient() {
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
//        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
//        Root<Client> clientRoot = query.from(Client.class);
//        query.select(clientRoot);
//        List<Client> resultList = session.createQuery(query).getResultList();
//
//        return resultList;
//    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public BankAccount getBankAccountById(long bankAccountId) {
        return sessionFactory.getCurrentSession()
                .load(BankAccount.class, bankAccountId);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void saveBankAccount (BankAccount bankAccount) {
          sessionFactory.getCurrentSession()
                  .merge(bankAccount);
    }

    @Override
    public void editBankAccount(BankAccount bankAccount) {
          sessionFactory.getCurrentSession().update(bankAccount);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deleteBankAccount(BankAccount bankAccount) {
        sessionFactory.getCurrentSession()
                .delete(bankAccount);
    }
}
