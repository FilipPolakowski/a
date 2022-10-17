CREATE DATABASE PizzaAPI;

CREATE TABLE Customer(Username varchar(12),
					CustomerPassword varchar(30),
					Address varchar(50),
					PhoneNumber varchar(13));
SELECT * FROM Customer;

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

							