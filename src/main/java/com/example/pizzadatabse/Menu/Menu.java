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

import java.sql.*;
import java.util.ArrayList;

public class Menu {
    private static boolean isDiscountApllied = false;
    private static Cart cart = new Cart("null");
    private static String userName;

    public static void displayMenu(Stage menuStage, String userName) {
        if(!userName.equals(cart.getUsername())) {
            cart = new Cart(userName);
        }
        GridPane menuGroup = new GridPane();
        menuGroup.setAlignment(Pos.CENTER);
        menuGroup.setHgap(15);
        menuGroup.setVgap(15);
        menuGroup.setPadding(new Insets(25, 25, 25, 25));
        Menu.userName = userName;

        menuStage.setTitle("Menu");


        menuStage.setScene(new Scene(menuGroup, 500, 500));
        EventHandler<ActionEvent> pizzas = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage pizzas = new Stage();
                GridPane pizzaGroup = new GridPane();
                pizzaGroup.setHgap(20);
                pizzaGroup.setVgap(20);
                pizzaGroup.setAlignment(Pos.BASELINE_LEFT);
                ArrayList<Pizza> listOfPizzas = getDataPizza("pizza", 3);


                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfPizzas.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfPizzas.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfPizzas.get(i).getName());
                    Name.setFont(new Font("Arial", 14));
                    Label Toppings = new Label(listOfPizzas.get(i).getToppings());
                    Toppings.setFont(new Font("Arial", 14));
                    Label Price = new Label(String.valueOf(listOfPizzas.get(i).getPrice() * 1.4 * 1.09));
                    Price.setFont(new Font("Arial", 14));
                    Label Vegetarian = new Label(listOfPizzas.get(i).getIsVegetarian());
                    Vegetarian.setFont(new Font("Arial", 14));
                    pizzaGroup.add(ID, 1, i);

                    pizzaGroup.add(Name, 2, i);
                    pizzaGroup.add(Toppings, 3, i);

                    pizzaGroup.add(Price, 4, i);

                    pizzaGroup.add(Vegetarian, 5, i);
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
                ArrayList<Item> listOfDrinksAndDesserts = getListOfDrinksAndDeserts();





                //TODO get all pizzas from database and add them to arrayList

                for (int i = 0; i < listOfDrinksAndDesserts.size(); i++) {
                    final int a = i;
                    Label ID = new Label(listOfDrinksAndDesserts.get(i).getID());
                    ID.setFont(new Font("Arial", 14));
                    Label Name = new Label(listOfDrinksAndDesserts.get(i).getName());
                    Name.setFont(new Font("Arial", 14));

                    Label Price = new Label(String.valueOf(listOfDrinksAndDesserts.get(i).getPrice() * 1.4 * 1.09));
                    Price.setFont(new Font("Arial", 14));

                    drAndDesGroup.add(ID, 1, i);

                    drAndDesGroup.add(Name, 2, i);

                    drAndDesGroup.add(Price, 3, i);

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
        EventHandler<ActionEvent> orderHistory = new EventHandler<>() {
            public void handle(ActionEvent e) {
                Stage orderStage = new Stage();
                GridPane orderGroup = new GridPane();
                orderGroup.setHgap(20);
                orderGroup.setVgap(20);
                orderGroup.setAlignment(Pos.BASELINE_LEFT);

                try {
                    String url = "jdbc:mysql://localhost:3306/pizzaapi";
                    String login = "abc";
                    String passwords = "password";
                    String query = "SELECT * FROM `orders` WHERE username = \""+userName+"\"";

                    Label Price = new Label(Double.toString(cart.calculatePrice() * 1.4 * 1.09));


                    Connection conn = DriverManager.getConnection(url, login, passwords);
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet results = stmt.executeQuery();
                    int counter= 1;
                    while (results.next()) {
                        Label ID = new Label(results.getString(1));
                        ID.setFont(new Font("Arial", 14));
                        Label Address = new Label(results.getString(3));
                        Address.setFont(new Font("Arial", 14));

                        Price.setFont(new Font("Arial", 14));
                        EventHandler<ActionEvent> deleteOrder = new EventHandler<>() {
                            public void handle(ActionEvent e) {
                                String query2 = "DELETE FROM `orders` WHERE `username` = \""+userName+"\" AND `id` = "+ ID.getText();
                                try {
                                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                                    stmt2.executeUpdate();
                                    Menu.displayMenu(orderStage, userName);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        };
                        Button remove = new Button("remove order");
                        remove.setOnAction(deleteOrder);
                        orderGroup.add(remove, 2, counter);


                        orderGroup.add(ID, 0, counter);
                        orderGroup.add(Address, 1, counter);
                        counter++;
                    }

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }


                orderStage.setScene(new Scene(orderGroup,500, 500));
                orderStage.show();




                }


            };
        EventHandler<ActionEvent> cartevent = new EventHandler<>() {
            public void handle(ActionEvent e) {

                menuStage.close();
                Stage cartStage = new Stage();
                GridPane cartGroup = new GridPane();
                EventHandler<ActionEvent> placeOrder = new EventHandler<>() {
                    public void handle(ActionEvent e) {
                    Stage successfulPayment = new Stage();
                        try {
                            cart.placeOrder();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        GridPane pane = new GridPane();
                    Label payment = new Label("Payment was successful");
                        EventHandler<ActionEvent> goToActiveOrders = new EventHandler<>() {
                            public void handle(ActionEvent e) {

                            }
                        };
                    Button goToTrackingOrders = new Button("See order history");
                    goToTrackingOrders.setOnAction(orderHistory);
                    pane.add(payment,0,0);
                    pane.add(goToTrackingOrders,0,1);
                    successfulPayment.setScene(new Scene(pane, 500, 500));
                    successfulPayment.show();
                    cartStage.close();


                    }
                };
                int i = 0;
                for (i = 0; i < cart.getOrder().size(); i++) {
                    Label name = new Label(cart.getOrder().get(i).getName());
                    Label price = new Label(Double.toString(cart.getOrder().get(i).getPrice()));
                    cartGroup.add(name,0,i);
                    cartGroup.add(price,1,i);

                }
                TextField discount = new TextField();
                Label Price;
                if(isDiscountApllied){
                    Price = new Label(Double.toString(cart.calculatePrice() * 1.4 * 1.09*0.9));

                }
                else{
                    Price = new Label(Double.toString(cart.calculatePrice() * 1.4 * 1.09));

                }
                cartGroup.add(Price, 0, i);
                EventHandler<ActionEvent> applyDiscount = new EventHandler<>() {
                    public void handle(ActionEvent e) {
                        String sql_res= "SELECT * FROM `discounts` WHERE `id`="+discount.getText();
                        String url = "jdbc:mysql://localhost:3306/pizzaapi";
                        String login = "abc";
                        String passwords = "password";

                        try {
                            Connection conn = DriverManager.getConnection(url, login, passwords);

                            try {
                                PreparedStatement stmt2 = conn.prepareStatement(sql_res);
                                ResultSet set = stmt2.executeQuery();
                                if(set.next()){
                                    System.out.println("aaa");
                                    isDiscountApllied = true;
                                    String query2 = "DELETE FROM `discounts` WHERE `id` = "+discount.getText();
                                    PreparedStatement stmt5 = conn.prepareStatement(query2);
                                    Menu.displayMenu(cartStage, userName);
                                    stmt5.execute();

                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }



                    }
                };
                Button applyDiscounts = new Button("Apply discount");
                applyDiscounts.setOnAction(applyDiscount);
                cartGroup.add(applyDiscounts,2,i);
                cartGroup.add(discount,1,i);


                Button pay = new Button("Pay");
                pay.setOnAction(placeOrder);
                cartGroup.add(pay, 1,i);

                Scene cartScene = new Scene(cartGroup, 500, 500);

                cartStage.setScene(cartScene);
                cartStage.show();

            }


        };


        Button pizza = new Button("Pizzas");
        menuGroup.add(pizza, 0, 0);
        pizza.setOnAction(pizzas);

        Button deserts = new Button("Deserts");
        menuGroup.add(deserts, 1, 0);
        deserts.setOnAction(desertsAndDrinks);

        Button order_history = new Button("Order history");
        menuGroup.add(order_history, 1, 1);
        order_history.setOnAction(orderHistory);

        Button cart = new Button("Shopping cart");
        menuGroup.add(cart, 0, 1);
        cart.setOnAction(cartevent);
    }

    private static ArrayList<Item> getListOfDrinksAndDeserts() {
        ArrayList<Item> drinks = new ArrayList<>();
        try {
            String url = "jdbc:mysql://localhost:3306/pizzaapi";
            String login = "abc";
            String passwords = "password";
            String query = "SELECT * FROM `deserts_drinks`";

            Connection conn = DriverManager.getConnection(url, login, passwords);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                drinks.add(new DesertsAndDrinks(results.getString(2), results.getDouble(3), results.getString(1)));

            }


        } catch (Exception a) {
            throw new IllegalStateException("Cannot find database", a);
        }
        return drinks;
    }

    public static ArrayList<Pizza> getDataPizza(String whichData, int howManyColumns) {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/pizzaapi";
            String login = "abc";
            String passwords = "password";
            String query = "SELECT * FROM `" + whichData + "`";

            Connection conn = DriverManager.getConnection(url, login, passwords);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                pizzas.add(new Pizza(results.getString(2), getDataToppings(results.getString(3)), results.getString(1)));



            }


        } catch (Exception a) {
            throw new IllegalStateException("Cannot find database", a);
        }
        return pizzas;
    }

    public static ArrayList<Topping> getDataToppings(String whichToppings) {
        ArrayList<Topping> toppings = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < whichToppings.length(); i+=2) {
            strings.add(whichToppings.substring(i, i + 2));
            try {
                String url = "jdbc:mysql://localhost:3306/pizzaapi";
                String login = "abc";
                String passwords = "password";
                String query = "SELECT * FROM `toppings` WHERE topping_id = "+strings.get(i/2);
                Connection conn = DriverManager.getConnection(url, login, passwords);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet results = stmt.executeQuery();
                while(results.next()){
                toppings.add(new Topping(results.getString(2),
                        results.getDouble(3),
                        results.getInt(4) == 1,
                        results.getString(1)));}
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return toppings;

    }
}
