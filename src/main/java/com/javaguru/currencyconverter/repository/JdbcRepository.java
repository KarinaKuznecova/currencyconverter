package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.CurrencyPair;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Profile("jdbc")
public class JdbcRepository implements CurrencyRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<CurrencyPair> findPairById(Long id) {
        String query = "select * from currencypair where id = " + id;
        List<CurrencyPair> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(CurrencyPair.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    public void setFee(Long id, BigDecimal fee) {
        String query = "update currencypair set fee = '" + fee + "' where id = " + id;
        jdbcTemplate.execute(query);
    }

    public void deleteFee(Long id) {
        String query = "update currencypair set fee = '" + CurrencyPair.DEFAULT_FEE + "' where id = " + id;
        jdbcTemplate.execute(query);
    }

    public Optional<BigDecimal> getFee(Long id){
        String query = "select fee from currencypair where id = " + id;
        List<CurrencyPair> pairs = jdbcTemplate.query(query, new BeanPropertyRowMapper(CurrencyPair.class));
        if (!pairs.isEmpty()) {
            return Optional.ofNullable(pairs.get(0).getFee());
        }
        return Optional.empty();
    }

    public void printRates(){
        String query = "select * from currencypair";
        List<CurrencyPair> currencyPairs = jdbcTemplate.query(query, new BeanPropertyRowMapper(CurrencyPair.class));
        for (CurrencyPair i : currencyPairs) {
            System.out.println(i);
            System.out.println();
        }
    }
}
