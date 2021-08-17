



Category table -1
Create table productcategory(categoryid number(15) primary key,name varchar2(20),description varchar2(800));

sweetItem table-2
create table sweetitem(sweetitemid number(15) primary key, sweetitemname VARCHAR2(15),price number(8,2),
categoryid number(15) references productcategory(categoryid));

Cart table-3
Create table cart(cartid number(15) primary key, grandtotal number(8,2),total number(8,2), sweetitemcount number(15),sweetitemid number(15) references sweetitem(sweetitemid));

SweetOrder table-5

Create table sweetorder(sweetorderid number(15) primary key,createddate date,customerid number(15) references customer(customerid));

Customer table-4
Create table customer(customerid number(15) primary key,username varchar2(15),password varchar2(15),address varchar2(50),cartid number(15) references cart(cartid));


orderBill table-6
create table orderbill(orderbillid number(15) primary key,createddate date,totalcost number(8,2),sweetorderid number(15) references sweetorder(sweetorderid) );

step-7
alter table customer add type varchar2(15);
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

1
insert into productcategory values(1,'laddu','Sweetest laddu of all time');

2
insert into sweetitem values(1,'Chocolate Laddu',100,1);

3
insert into cart values(1,200,100,2,1);

4
insert into customer values(1,'Amrit','password','Trinity Square, Heaven, LosAngeles',1,'1');

5
insert into sweetorder values(1,'10-Aug-2021',1);
6
insert into orderbill values(1,'10-Aug-2021',200,1)


7
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;

update customer set type='1' where customerid=1;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


for orderbill--------6

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderbill_seq")
@SequenceGenerator(name = "orderbill_seq", sequenceName="orderbill_seq", allocationSize=1)

CREATE SEQUENCE orderbill_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
-------------------
for cart---------3

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
@SequenceGenerator(name = "cart_seq", sequenceName="cart_seq", allocationSize=1)

CREATE SEQUENCE cart_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
-----------------------
for customer------------4

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
@SequenceGenerator(name = "customer_seq", sequenceName="customer_seq", allocationSize=1)

CREATE SEQUENCE customer_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
-----------------------
for product category---1

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
@SequenceGenerator(name = "prod_seq", sequenceName="prod_seq", allocationSize=1)

CREATE SEQUENCE prod_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
------------------------
for sweetitem--------2
 
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweeti_seq")
@SequenceGenerator(name = "sweeti_seq", sequenceName="sweeti_seq", allocationSize=1)

CREATE SEQUENCE sweeti_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
----------------------
for sweetorder----------5

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweeto_seq")
@SequenceGenerator(name = "sweeto_seq", sequenceName="sweeto_seq", allocationSize=1)

CREATE SEQUENCE sweeto_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

