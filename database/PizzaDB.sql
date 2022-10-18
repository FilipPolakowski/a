DROP DATABASE pizzaapi;
CREATE DATABASE Pizzaapi;
USE pizzaapi;
GRANT ALL PRIVILEGES ON pizzaapi TO 'root'@'localhost' WITH GRANT OPTION;



CREATE TABLE `pizzaapi`.`customer`(`username` varchar(12),
					`customer_password` varchar(30),
					`address` varchar(50),
                    `postcode` varchar(6),
					`phone_number` varchar(13));
SELECT * FROM `customer`;
DROP TABLE `customer`;
CREATE TABLE `delivery_drivers`(`id` int,
								`postcode` varchar(6));
INSERT delivery_drivers(id)
VALUE (1);
INSERT delivery_drivers(id)
VALUE (2);
INSERT delivery_drivers(id)
VALUE (3);
INSERT delivery_drivers(id)
VALUE (4);
INSERT delivery_drivers(id)
VALUE (5);
SELECT * FROM `delivery_drivers`;



CREATE TABLE `toppings`(`topping_id` varchar(4),
                        `sort` varchar(30),
						`price` double,
                        `vegetarian` BIT);
SELECT * FROM `toppings`;

CREATE TABLE `deserts_drinks`(`id` varchar(4),
							`item` varchar(12),
                            `price` double);
SELECT * FROM `deserts_drinks`;
DROP TABLE orders;

CREATE TABLE `pizza`(`id` varchar(4),
					`item` varchar(12),
                    `topping_id_list` varchar(30));
SELECT * FROM `pizza`;

CREATE TABLE `orders`(`id`varchar(4),
					`username` varchar(12),
                    `address` char(50),
                    `postcode` char(6),
                    `dates` varchar(20),
                    `times` varchar(8),
                    `date_and_time` varchar(25),
                    `delivery_status` BIT);
Update orders SET date_and_time = str_to_date(date_and_time, "%d/%m/%Y");
SELECT * FROM `orders`;
INSERT INTO orders(id,username,address,postcode,dates,times, delivery_status)
VALUES (1,2,3,4,12-2-2020, 12-34 , 0);
DROP TABLE orders;



CREATE TABLE `discount`(`discount_code`varchar(6),
						`is_used` BIT);
SELECT * FROM `discount`;
INSERT INTO pizza (id,item,topping_id_list)
VALUES (1,'Cheese','010102');

INSERT INTO toppings (topping_id,sort,price,vegetarian)
VALUES (1,'Cheese','3',1);
INSERT INTO toppings (topping_id,sort,price,vegetarian)
VALUES (2,'Bacon','4',0);
INSERT INTO toppings (topping_id,sort,price,vegetarian)
VALUES (3,'Ham','2',0);
INSERT INTO toppings (topping_id,sort,price,vegetarian)
VALUES (4,'Olives','3',1);
INSERT INTO toppings (topping_id,sort,price,vegetarian)
VALUES (5,'Chicken','3',0);
INSERT INTO customer (username, customer_password, address, phone_number)
VALUES ('rafafa', '123pass', '123 beach street', '12345678'); 