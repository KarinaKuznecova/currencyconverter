package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.CurrencyPair;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Profile("hibernate")
public class HibernateRepository implements CurrencyRepository {

    private final SessionFactory sessionFactory;

    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<CurrencyPair> findPairById(Long id) {
        CurrencyPair currencyPair = (CurrencyPair) sessionFactory.getCurrentSession().createCriteria(CurrencyPair.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(currencyPair);
    }

    public void setFee(Long id, BigDecimal fee) {
        CurrencyPair currencyPair = findPairById(id).orElseThrow(() ->
                new IllegalArgumentException("Currency pair with id " + id + " not found"));
        currencyPair.setFee(fee);
        sessionFactory.getCurrentSession().saveOrUpdate(currencyPair);
    }

    public void deleteFee(Long id) {
        CurrencyPair currencyPair = findPairById(id).orElseThrow(() ->
                new IllegalArgumentException("Currency pair with id " + id + " not found"));
        currencyPair.removeFee();
        sessionFactory.getCurrentSession().saveOrUpdate(currencyPair);
    }

    public Optional<BigDecimal> getFee(Long id){
        CurrencyPair currencyPair = (CurrencyPair) sessionFactory.getCurrentSession().createCriteria(CurrencyPair.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(currencyPair.getFee());
    }

    public void printRates(){
        List<CurrencyPair> currencyPairs = sessionFactory.getCurrentSession().createCriteria(CurrencyPair.class).list();

        for (CurrencyPair i : currencyPairs) {
            System.out.println(i);
            System.out.println();
        }
    }
}
