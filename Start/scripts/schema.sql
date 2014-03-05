create table Customer(cust_id int NOT NULL AUTO_INCREMENT,cust_name text,cust_address text,cust_contact text,PRIMARY KEY (cust_id));
create table Order_Details(order_id int NOT NULL AUTO_INCREMENT,description text,qty int,unit text,gross_price float,net_value float,PRIMARY KEY(order_id));

create table Customer_Order(order_id int,cust_id int,cust_name text,status text,FOREIGN KEY (order_id) REFERENCES Order_Details(order_id),FOREIGN KEY (cust_id) REFERENCES Customer(cust_id));
create table Chemical_Composition(order_id int,chem_name text,min_level float,max_level float,actual_proportion float,FOREIGN KEY (order_id) REFERENCES Order_Details(order_id));
create table Mechanical_Composition(order_id int,PARTICULAR text,min_level float,max_level float,actual_proportion float,FOREIGN KEY (order_id) REFERENCES Order_Details(order_id));



list of elements :

Sn
Zn
Pb
Ni
Al
Si
Fe
Sb
Cu

Customer Table
+--------------+---------+------+-----+---------+----------------+
| Field        | Type    | Null | Key | Default | Extra          |
+--------------+---------+------+-----+---------+----------------+
| cust_id      | int(11) | NO   | PRI | NULL    | auto_increment |
| cust_name    | text    | YES  |     | NULL    |                |
| cust_address | text    | YES  |     | NULL    |                |
| cust_contact | text    | YES  |     | NULL    |                |
+--------------+---------+------+-----+---------+----------------+

Order_Details Table
+-------------+---------+------+-----+---------+----------------+
| Field       | Type    | Null | Key | Default | Extra          |
+-------------+---------+------+-----+---------+----------------+
| order_id    | int(11) | NO   | PRI | NULL    | auto_increment |
| material    | int(11) | YES  |     | NULL    |                |
| description | text    | YES  |     | NULL    |                |
| qty         | int(11) | YES  |     | NULL    |                |
| unit        | text    | YES  |     | NULL    |                |
| gross_price | float   | YES  |     | NULL    |                |
| net_value   | float   | YES  |     | NULL    |                |
+-------------+---------+------+-----+---------+----------------+

Customer_Order Table
+-----------+---------+------+-----+---------+-------+
| Field     | Type    | Null | Key | Default | Extra |
+-----------+---------+------+-----+---------+-------+
| order_id  | int(11) | YES  | MUL | NULL    |       |
| cust_id   | int(11) | YES  | MUL | NULL    |       |
| cust_name | text    | YES  |     | NULL    |       |
| status    | text    | YES  |     | NULL    |       |
+-----------+---------+------+-----+---------+-------+

Chemical_Composition Table
+-------------------+---------+------+-----+---------+-------+
| Field             | Type    | Null | Key | Default | Extra |
+-------------------+---------+------+-----+---------+-------+
| order_id          | int(11) | YES  | MUL | NULL    |       |
| chem_name         | text    | YES  |     | NULL    |       |
| min_level         | float   | YES  |     | NULL    |       |
| max_level         | float   | YES  |     | NULL    |       |
| actual_proportion | float   | YES  |     | NULL    |       |
+-------------------+---------+------+-----+---------+-------+


Mechanical Composition Table
+-------------------+---------+------+-----+---------+-------+
| Field             | Type    | Null | Key | Default | Extra |
+-------------------+---------+------+-----+---------+-------+
| order_id          | int(11) | YES  | MUL | NULL    |       |
| PARTICULAR        | text    | YES  |     | NULL    |       |
| min_level         | float   | YES  |     | NULL    |       |
| max_level         | float   | YES  |     | NULL    |       |
| actual_proportion | float   | YES  |     | NULL    |       |
+-------------------+---------+------+-----+---------+-------+



insert into Customer values(,'Viraj Profiles Ltd.','Mahagaon,Thane,Maharashtra','9049991021/24');
insert into Customer values(2,'F Motors Ltd.','Malegaon,Nashik,Maharashtra','99999999999');

insert into Order_Details values(101,'BUSH Item No.10,88884444',1,'1',1080,10000);
insert into Order_Details values(102,'DISHER Item No.1,777884444',2,'2',10080,1000000);


insert into Customer_Order values(101,1,'Viraj Profiles Ltd.','Pending');
insert into Customer_Order values(102,2,'F Motors Ltd.','Completed');


insert into Chemical_Composition values(101,'Sn',1,10,5);
insert into Chemical_Composition values(101,'Zn',1,5,3.5);

GENERAL OVERVIEW OF ALL CUstomer Order :
_______________________________________
select * from Customer_Order;
_______________________________________


_______________________________________
CUSTOMER_INFORMATION :
_______________________________________
select * from Customer;
_______________________________________


_______________________________________
ALL ORDERS :
_______________________________________
select * from Order_Details;
_______________________________________


_______________________________________
ORDER_ID wise search :
_______________________________________
select * from Order_Details where order_id='101';
_______________________________________


_______________________________________
CUSTOMER NAME wise search :
_______________________________________
select * from Customer_Order where cust_name='Viraj Profiles Ltd.';
_______________________________________


_______________________________________
STATUS wise search :
_______________________________________
select * from Customer_Order where status='Pending';
select * from Customer_Order where status='Completed';
_______________________________________


_______________________________________
CHEMICAL_COMPOSITION order wise search :
_______________________________________
select * from Chemical_Composition where order_id='101';
_______________________________________




