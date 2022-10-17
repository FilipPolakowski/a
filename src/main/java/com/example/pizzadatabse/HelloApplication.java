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

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;

public class HelloApplication extends Application {

    public String lastLogin = "";
    public String lastPassword = "";

    @Override
    public void start(Stage login) throws IOException {
        GridPane loginGroup = new GridPane();
        loginGroup.setAlignment(Pos.CENTER);
        loginGroup.setHgap(15);
        loginGroup.setVgap(15);
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

                if(tryToLogin()){
                    Menu.displayMenu(login);
                };

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

    private boolean tryToLogin() {
        //TODO implement check whether the user is in database
        return true;
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



        TextField username = new TextField();
        TextField password = new TextField();
        TextField password2 = new TextField();

        loginGroup.add(username,1,1);
        loginGroup.add(password,1,2);
        loginGroup.add(password2,1,3);
        loginGroup.add(passwordText2, 0, 3);

        loginGroup.add(usernameText,0,1);
        loginGroup.add(passwordText,0,2);

        Button tryToRegister = new Button("register");
        loginGroup.add(tryToRegister, 2,0);
        registration.setScene(new Scene(loginGroup, 500, 500));
        EventHandler<ActionEvent> loggIn = new EventHandler<>() {
            public void handle(ActionEvent e) {

                if(password2.getText().equals(password.getText())){

                };

            }
        };
    }

//    private static void Post(String username, String password) throws Exception{
//        try{
//            Connection Conn = getConnection();
//        }
//    }

//    public static Connection getConnection() throws Exception{}

    public static void main(String[] args) throws Exception {
        getConnection();
//        launch();
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.driver";
            String url = "jdbc:mysql://localhost:3306/PizzaAPI";
            String username = "root";
            String password = "SSATAEi2002!123";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");

            return conn;
        } catch(Exception e){System.out.println(e);}

        return null;
    }
}