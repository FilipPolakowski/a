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