DROP DATABASE pizzaapi;
CREATE DATABASE Pizzaapi;
USE pizzaapi;
GRANT ALL PRIVILEGES ON pizzaapi TO 'abc'@'localhost' WITH GRANT OPTION;
SET FOREIGN_KEY_CHECKS=0;
SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE `pizzaapi`.`customer`(`username` varchar(12),
					`customerPassword` varchar(30),
					`address` varchar(50),
					`phoneNumber` varchar(13));
SELECT * FROM `customer`;

CREATE TABLE Toppings(ToppingID varchar(4),
                        Sort varchar(30),
						Price double,
                        Vegetarian BIT);
SELECT * FROM Toppings;

CREATE TABLE DesertsDrinks(ID varchar(4),
							Item varchar(12),
                            Price double);
SELECT * FROM DesertsDrinks;

CREATE TABLE Pizza(ID varchar(4),
					Item varchar(12),
                    ToppingIDList varchar(30));
SELECT * FROM Pizza;

CREATE TABLE Orders(ID varchar(4),
					Username varchar(12),
                    Dates varchar(10),
                    Times varchar(8));
SELECT * FROM Orders;