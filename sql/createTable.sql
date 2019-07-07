create table currencyPair (
id bigint not null auto_increment,
primaryCurrency varchar(10),
secondaryCurrency varchar(10),
rate decimal(14,4),
fee decimal(4,2),
primary key (id));