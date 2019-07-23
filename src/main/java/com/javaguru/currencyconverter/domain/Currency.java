package com.javaguru.currencyconverter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties
@Entity(name = "Currency")
@Table
public class Currency {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "base")
    private String base;

    @Column(name = "date")
    private String date;

    @Column(name = "rates")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Set<Rate> rates;

    public Currency(String base, String date, Set<Rate> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Currency() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public void addRate(Rate rate) {
        rates.add(rate);
    }

    public void removeRate(Rate rate) {
        rates.remove(rate);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) &&
                Objects.equals(base, currency.base) &&
                Objects.equals(date, currency.date) &&
                Objects.equals(rates, currency.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, base, date, rates);
    }
}
