package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.Currency;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CurrencyRepository {

    private final SessionFactory sessionFactory;

    public CurrencyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Currency> findCurrencyById(Long id) {
        Currency currency = (Currency) sessionFactory.getCurrentSession().createCriteria(Currency.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(currency);
    }

    public Currency saveOrUpdateCurrency(Currency currency) {
        sessionFactory.getCurrentSession().saveOrUpdate(currency);
        return currency;
    }

    public void removeCurrency(Currency currency) {
        sessionFactory.getCurrentSession().delete(currency);
    }

    public void printRates() {
        List<Currency> currency = sessionFactory.getCurrentSession().createCriteria(Currency.class).list();

        currency.forEach((Currency cur) -> System.out.println(cur));
    }

    public Optional<Currency> getByName(String name) {
        String query = "select * from currency where base = '" + name + "'";
        SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery(query);
        query1.addEntity(Currency.class);
        List<Currency> currencies = query1.list();
        return Optional.ofNullable(currencies.get(0));
    }
}
