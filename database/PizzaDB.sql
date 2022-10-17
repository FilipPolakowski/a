DROP DATABASE pizzaapi;
CREATE DATABASE Pizzaapi;
USE pizzaapi;
GRANT ALL PRIVILEGES ON pizzaapi TO 'abc'@'localhost' WITH GRANT OPTION;



CREATE TABLE `pizzaapi`.`customer`(`username` varchar(12),
					`customer_password` varchar(30),
					`address` varchar(50),
					`phone_number` varchar(13));
SELECT * FROM `customer`;


CREATE TABLE `toppings`(`topping_id` varchar(4),
                        `sort` varchar(30),
						`price` double,
                        `vegetarian` BIT);
SELECT * FROM `toppings`;

CREATE TABLE `deserts_drinks`(`id` varchar(4),
							`item` varchar(12),
                            `price` double);
SELECT * FROM `deserts_drinks`;

CREATE TABLE `pizza`(`id` varchar(4),
					`item` varchar(12),
                    `topping_id_list` varchar(30));
SELECT * FROM `pizza`;

CREATE TABLE `orders`(ID varchar(4),
					`username` varchar(12),
                    `dates` varchar(10),
                    `times` varchar(8));
SELECT * FROM `orders`;
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