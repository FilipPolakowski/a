package com.example.pizzadatabse;

import com.example.pizzadatabse.Menu.Menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.sql.*;

import java.io.IOException;

public class HelloApplication extends Application {

    public String lastLogin = "";
    public String lastPassword = "";

    @Override
    public void start(Stage login) throws IOException {
        GridPane loginGroup = new GridPane();
        loginGroup.setAlignment(Pos.CENTER);
        loginGroup.setHgap(25);
        loginGroup.setVgap(25);
        loginGroup.setPadding(new Insets(25, 25, 25, 25));


        login.setTitle("Login");
        Label usernameText = new Label("insert your username");
        Label passwordText = new Label("insert your password");


        TextField username = new TextField();
        TextField password = new TextField();
        loginGroup.add(username,1,1);
        loginGroup.add(password,1,2);
        loginGroup.add(usernameText,0,1);
        loginGroup.add(passwordText,0,2);

        Button signUp = new Button("Make a account");
        Button logIn = new Button("Log in");

        EventHandler<ActionEvent> register = new EventHandler<>() {
            public void handle(ActionEvent e) {

                    registerCustomer(login);

            }
        };
        EventHandler<ActionEvent> loggIn = new EventHandler<>() {
            public void handle(ActionEvent e) {

                try {
                    if(tryToLogin(username, password)){
                        Menu.displayMenu(login);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                ;

            }
        };
        signUp.setOnAction(register);
        loginGroup.add(signUp,0,3);
        logIn.setOnAction(loggIn);
        loginGroup.add(logIn,1,3);


        Scene scene = new Scene(loginGroup, 500, 500);

        login.setScene(scene);
        login.show();

    }

    private boolean tryToLogin(TextField username, TextField password) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT (`username`,`customer_password`) FROM (?,?)");
        ps.executeQuery();
        ResultSet results = ps.getResultSet();
        System.out.println(results.getString(1));
        if(results.getString(1).equals(username.toString()) && results.getString(2).equals(password.toString())){
            return true;
        }
        return false;
    }

    private void registerCustomer(Stage registration) {

        GridPane loginGroup = new GridPane();
        loginGroup.setAlignment(Pos.CENTER);
        loginGroup.setHgap(15);
        loginGroup.setVgap(15);
        loginGroup.setPadding(new Insets(25, 25, 25, 25));


        registration.setTitle("Sign up");
        Label usernameText = new Label("insert your username");
        Label passwordText = new Label("insert your password");
        Label passwordText2 = new Label("confirm your password");
        Label addressText = new Label("enter your address");
        Label postcodeText = new Label("enter your postcode");
        Label phoneNumberText = new Label("enter your phone number");

        TextField username = new TextField();
        TextField password = new TextField();
        TextField password2 = new TextField();
        TextField address = new TextField();
        TextField postcode = new TextField();
        TextField phoneNumber = new TextField();

        loginGroup.add(username,1,1);
        loginGroup.add(password,1,2);
        loginGroup.add(password2,1,3);
        loginGroup.add(address,1, 4);
        loginGroup.add(postcode,1, 5);
        loginGroup.add(phoneNumber, 1, 6);

        EventHandler<ActionEvent> loggIn = new EventHandler<>() {
            public void handle(ActionEvent e) {

                if(password2.getText().equals(password.getText())){
                    try{
                        String url = "jdbc:mysql://localhost:3306/pizzaapi";
                        String login = "root";
                        String passwords = "SSATAEi2002!123";

                        Connection conn = DriverManager.getConnection(url,login,passwords);
                        PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer(`username`, `customer_password`, `address`, `postcode`, `phone_number`) VALUES (?,?,?,?,?)");
                        stmt.setString(1, username.getText());
                        stmt.setString(2, password.getText());
                        stmt.setString(3, address.getText());
                        stmt.setString(4, phoneNumber.getText());
                        stmt.setString(5, phoneNumber.getText());



                        stmt.executeUpdate();

                    } catch(Exception a){
                        throw new IllegalStateException("Cannot find database", a);
                    }
                };

            }
        };

        loginGroup.add(usernameText,0,1);
        loginGroup.add(passwordText,0,2);
        loginGroup.add(passwordText2, 0, 3);
        loginGroup.add(addressText,0,4);
        loginGroup.add(postcodeText, 0, 5);
        loginGroup.add(phoneNumberText, 0, 6);

        Button tryToRegister = new Button("register");
        tryToRegister.setOnAction(loggIn);
        loginGroup.add(tryToRegister, 2,0);
        registration.setScene(new Scene(loginGroup, 500, 500));

    }

    public static void main(String[] args) throws Exception {
        getConnection();
        launch();
    }

    public static Connection getConnection() throws Exception{
        try{
            String url = "jdbc:mysql://localhost:3306/PizzaAPI";
            String username = "root";
            String password = "SSATAEi2002!123";

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");

            return conn;
        } catch(Exception e){
            throw new IllegalStateException("Cannot find database", e);
        }

    }
}