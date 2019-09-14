package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.Rate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RatesRepository {

    private final SessionFactory sessionFactory;

    public RatesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Rate> findRatesById(Long id) {
        Rate rate = (Rate) sessionFactory.getCurrentSession().createCriteria(Rate.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(rate);
    }

    public Rate createOrUpdateRate(Rate rate) {
        sessionFactory.getCurrentSession().saveOrUpdate(rate);
        return rate;
    }

    public void setFee(Long id, BigDecimal fee) {
        Rate rate = findRatesById(id).orElseThrow(() ->
                new IllegalArgumentException("Rate with id " + id + " not found"));
        rate.setFee(fee);
        sessionFactory.getCurrentSession().saveOrUpdate(rate);
    }

    public void deleteFee(Long id) {
        Rate rate = findRatesById(id).orElseThrow(() ->
                new IllegalArgumentException("Rate with id " + id + " not found"));
        rate.removeFee();
        sessionFactory.getCurrentSession().saveOrUpdate(rate);
    }

    public Optional<BigDecimal> getFee(Long id) {
        Rate rate = (Rate) sessionFactory.getCurrentSession().createCriteria(Rate.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(rate.getFee());
    }

    public void printRates() {
        List<Rate> rates = sessionFactory.getCurrentSession().createCriteria(Rate.class).list();

        rates.stream().forEach((Rate rate) -> System.out.println(rate));
    }
}
