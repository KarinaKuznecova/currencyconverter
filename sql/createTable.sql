create table currencyPair (
id bigint not null auto_increment,
primaryCurrency varchar(10),
secondaryCurrency varchar(10),
rate decimal(14,4),
fee decimal(4,2),
primary key (id));

create table if not exists currency (
id bigint not null auto_increment,
base varchar(4),
date varchar(15),
primary key (id));

create table if not exists rates (
id bigint not null auto_increment,
secondary_currency varchar(4),
rate decimal(14,4),
fee decimal(4,2),
currency_id bigint,
CONSTRAINT currency_rates_fk FOREIGN KEY (currency_id) REFERENCES currency(id),
primary key (id));

commit;
