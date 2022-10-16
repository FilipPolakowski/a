package com.example.pizzadatabse.Menu;


import com.example.pizzadatabse.Menu.MenuItems.*;
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

                top1.add(new Topping("cheese", 1, true));
                top2.add(new Topping("cheese", 1, true));
                top3.add(new Topping("cheese", 1, true));
                top4.add(new Topping("cheese", 1, true));
                top1.add(new Topping("bread", 1, true));
                top2.add(new Topping("bread", 1, true));
                top3.add(new Topping("bread", 1, true));
                top4.add(new Topping("bread", 1, true));



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
                Stage pizzas = new Stage();
                GridPane pizzaGroup = new GridPane();
                pizzaGroup.setHgap(20);
                pizzaGroup.setVgap(20);
                pizzaGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Item> listOfDrinksAndDesserts = new ArrayList<>();






                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Tiramisu", 3,));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Ice Crea,", 4, "1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Cola", 2, "1"));
                listOfDrinksAndDesserts.add(new DesertsAndDrinks("Fanta", 1, "1"));



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
        Button pizza = new Button("Pizzas");
        menuGroup.add(pizza, 0,0);
        pizza.setOnAction(pizzas);

        Button deserts = new Button("Deserts");
        menuGroup.add(deserts, 1,0);
        pizza.setOnAction(pizzas);

        Button drinks = new Button("Drinks");
        menuGroup.add(drinks, 1,1);
        pizza.setOnAction(pizzas);

        Button cart = new Button("Shopping cart");
        menuGroup.add(cart, 0,1);
        pizza.setOnAction(pizzas);
    }
}
