package com.example.pizzadatabse.Menu;

import java.sql.*;

import com.example.pizzadatabse.Menu.MenuItems.*;
import com.example.pizzadatabse.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Menu {
    private static Cart cart = new Cart();
    public static void displayMenu(Stage menuStage){
        GridPane menuGroup = new GridPane();
        menuGroup.setAlignment(Pos.CENTER);
        menuGroup.setHgap(15);
        menuGroup.setVgap(15);
        menuGroup.setPadding(new Insets(25, 25, 25, 25));


        menuStage.setTitle("Menu");





        menuStage.setScene(new Scene(menuGroup, 500, 500));
        EventHandler<ActionEvent> pizzas = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage pizzas = new Stage();
                GridPane pizzaGroup = new GridPane();
                pizzaGroup.setHgap(20);
                pizzaGroup.setVgap(20);
                pizzaGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Pizza> listOfPizzas = new ArrayList<>();
                ArrayList<Topping> top1 = new ArrayList<>();
                ArrayList<Topping> top2 = new ArrayList<>();
                ArrayList<Topping> top3 = new ArrayList<>();
                ArrayList<Topping> top4 = new ArrayList<>();

                top1.add(new Topping("cheese", 1, true,"0"));
                top2.add(new Topping("cheese", 1, true,"1"));
                top3.add(new Topping("cheese", 1, true,"2"));
                top4.add(new Topping("cheese", 1, true,"3"));
                top1.add(new Topping("bread", 1, true,"4"));
                top2.add(new Topping("bread", 1, true,"5"));
                top3.add(new Topping("bread", 1, true,"7"));
                top4.add(new Topping("bread", 1, true,"8"));



                listOfPizzas.add(new Pizza("Margharitta", top1, "1"));
                listOfPizzas.add(new Pizza("Margharitta", top2, "1"));
                listOfPizzas.add(new Pizza("Margharitta", top3, "1"));
                listOfPizzas.add(new Pizza("Margharitta", top4, "1"));



                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfPizzas.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfPizzas.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfPizzas.get(i).getName());
                    Name.setFont(new Font("Arial", 14));
                    Label Toppings = new Label(listOfPizzas.get(i).getToppings());
                    Toppings.setFont(new Font("Arial", 14));
                    Label Price = new Label(String.valueOf(listOfPizzas.get(i).getPrice()*1.4*1.09));
                    Price.setFont(new Font("Arial", 14));
                    Label Vegetarian = new Label(listOfPizzas.get(i).getIsVegetarian());
                    Vegetarian.setFont(new Font("Arial", 14));
                    pizzaGroup.add(ID, 1,i);

                    pizzaGroup.add(Name, 2,i);
                    pizzaGroup.add(Toppings, 3,i);

                    pizzaGroup.add(Price, 4,i);

                    pizzaGroup.add(Vegetarian, 5,i);
                    EventHandler<ActionEvent> orderPizza = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            cart.addDish(listOfPizzas.get(a));
                        }
                    };
                    Button order = new Button("add to cart");
                    order.setOnAction(orderPizza);
                    pizzaGroup.add(order, 6, i);


                }
                Scene pizzaScene = new Scene(pizzaGroup, 1000, 500);
                pizzas.setScene(pizzaScene);
                pizzas.show();

            }
        };

        EventHandler<ActionEvent> desertsAndDrinks = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage drinksAndDesertss = new Stage();
                GridPane drAndDesGroup = new GridPane();
                drAndDesGroup.setHgap(20);
                drAndDesGroup.setVgap(20);
                drAndDesGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Item> listOfDrinksAndDesserts = new ArrayList<>();






                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Tiramisu", 3,"1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Ice Crea,", 4,"1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Cola", 2,"2"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Fanta", 1,"3"));



                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfDrinksAndDesserts.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfDrinksAndDesserts.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfDrinksAndDesserts.get(i).getName());
                    Name.setFont(new Font("Arial", 14));

                    Label Price = new Label(String.valueOf(listOfDrinksAndDesserts.get(i).getPrice()*1.4*1.09));
                    Price.setFont(new Font("Arial", 14));

                    drAndDesGroup.add(ID, 1,i);

                    drAndDesGroup.add(Name, 2,i);

                    drAndDesGroup.add(Price, 3,i);

                    EventHandler<ActionEvent> orderPizza = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            cart.addDish(listOfDrinksAndDesserts.get(a));
                        }
                    };
                    Button order = new Button("add to cart");
                    order.setOnAction(orderPizza);
                    drAndDesGroup.add(order, 4, i);


                }
                Scene pizzaScene = new Scene(drAndDesGroup, 1000, 500);
                drinksAndDesertss.setScene(pizzaScene);
                drinksAndDesertss.show();

            }
        };
        EventHandler<ActionEvent> cartevent = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage cartStage = new Stage();
                GridPane cartGroup = new GridPane();
                cartGroup.setHgap(20);
                cartGroup.setVgap(20);
                cartGroup.setAlignment(Pos.BASELINE_LEFT);










                //TODO get all pizzas from database and add them to arrayList
                int counter = 0;
                for (int i = 0; i < cart.getOrder().size(); i++) {
                    counter = i+1;
                    final int a = i;
                    Label ID = new Label(cart.getOrder().get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(cart.getOrder().get(i).getName());
                    Name.setFont(new Font("Arial", 14));

                    Label Price = new Label(String.valueOf(cart.getOrder().get(i).getPrice()*1.4*1.09));
                    Price.setFont(new Font("Arial", 14));

                    cartGroup.add(ID, 1,i);

                    cartGroup.add(Name, 2,i);

                    cartGroup.add(Price, 3,i);

                    EventHandler<ActionEvent> deleteOrder = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            cart.getOrder().remove(id);

                            cartStage.close();

                        }
                    };
                    Button delete = new Button("delete");
                    delete.setOnAction(deleteOrder);

                    cartGroup.add(delete, 4, i);


                }
                EventHandler<ActionEvent> pay = new EventHandler<>() {
                    public void handle(ActionEvent e) {
                        cartStage.close();
                        Stage payment = new Stage();
                        GridPane  paymen = new GridPane();
                        paymen.setAlignment(Pos.CENTER);
                        Label suceesful = new Label("Payment was accepted");
                        paymen.add(suceesful, 0, 0);
                        payment.setScene(new Scene(paymen, 300, 300));
                        payment.show();

                    }
                };
                TextField address = new TextField("Enter your address");
                Button order = new Button("Pay");
                cartGroup.add(order, 0 , counter);
                order.setOnAction(pay);
                Scene pizzaScene = new Scene(cartGroup, 1000, 500);
                cartStage.setScene(pizzaScene);
                cartStage.show();

            }
        };
        EventHandler<ActionEvent> orderHistory = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage ordersStage = new Stage();
                GridPane ordersGroup = new GridPane();
                ordersGroup.setHgap(20);
                ordersGroup.setVgap(20);
                ordersGroup.setAlignment(Pos.BASELINE_LEFT);



                ArrayList<Order> ord= new ArrayList<>();





                //TODO get all orders of customer starting with active ones from database and add them to arrayList
                int counter = 0;
                for (int i = 0; i < ord.size(); i++) {
                    counter = i+1;
                    final int a = i;
                    Label ID = new Label(ord.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label timeWhenOrdered = new Label(ord.get(i).whenOrdered().toString());
                    timeWhenOrdered.setFont(new Font("Arial", 14));

                    Label Price = new Label(String.valueOf(ord.get(i).getPrice()*1.4*1.09));
                    Price.setFont(new Font("Arial", 14));

                    ordersGroup.add(ID, 1,i);

                    ordersGroup.add(timeWhenOrdered, 2,i);

                    ordersGroup.add(Price, 3,i);

                    EventHandler<ActionEvent> deleteOrder = new EventHandler<>() {
                        public void handle(ActionEvent e) {
                            int id = a;
                            if(ChronoUnit.MINUTES.between(ord.get(id).whenOrdered(), LocalTime.now())>5) {
                                //TODO remove order with id a from database
                            }
                            else {
                                Stage tooLate = new Stage();
                                GridPane tooLateGroup = new GridPane();
                                tooLateGroup.setHgap(20);
                                tooLateGroup.setVgap(20);
                                timeWhenOrdered.setAlignment(Pos.CENTER);
                                Label toooLate = new Label("You can't cancel and order after 5 minutes.");
                                tooLateGroup.add(toooLate, 0, 0);
                                tooLate.setScene(new Scene(tooLateGroup, 100, 200));
                                tooLate.show();

                            }
                        }
                    };
                    Button delete = new Button("cancel");
                    delete.setOnAction(deleteOrder);

                    ordersGroup.add(delete, 4, i);


                }
                EventHandler<ActionEvent> pay = new EventHandler<>() {
                    public void handle(ActionEvent e) {
                        ordersStage.close();
                        Stage payment = new Stage();
                        GridPane  paymen = new GridPane();
                        paymen.setAlignment(Pos.CENTER);
                        Label suceesful = new Label("Payment was accepted");
                        paymen.add(suceesful, 0, 0);
                        payment.setScene(new Scene(paymen, 300, 300));
                        payment.show();

                    }
                };
                TextField address = new TextField("Enter your address");
                Button order = new Button("Pay");
                ordersGroup.add(order, 0 , counter);
                order.setOnAction(pay);
                Scene orderScene = new Scene(ordersGroup, 1000, 500);
                ordersStage.setScene(orderScene);
                ordersStage.show();

            }
        };


        Button pizza = new Button("Pizzas");
        menuGroup.add(pizza, 0,0);
        pizza.setOnAction(pizzas);

        Button deserts = new Button("Deserts and drinks");
        menuGroup.add(deserts, 1,0);
        deserts.setOnAction(desertsAndDrinks);

        Button drinks = new Button("Order history");
        menuGroup.add(drinks, 1,1);
        pizza.setOnAction(pizzas);

        Button cart = new Button("Shopping cart");
        menuGroup.add(cart, 0,1);
        cart.setOnAction(cartevent);
    }
}
