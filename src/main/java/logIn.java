/**
 * Created by OzorakM on 25.7.2018.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class logIn extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        primaryStage.setTitle("Login");

        //Button submit
        Button btn = new Button();
        btn.setText("Submit credentials");

        Button reg = new Button();
        reg.setText("Register");
        reg.setLayoutX(160);
        reg.setLayoutY(250);
        reg.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                final Pane secondaryLayout = new Pane();
                final Scene secondScene = new Scene(secondaryLayout, 468, 353);

                Stage newWindow = new Stage();
                newWindow.setTitle("Registration page");
                newWindow.setScene(secondScene);

                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                final Button register = new Button();
                register.setLayoutX(190);
                register.setLayoutY(250);
                register.setText("Register account");
                register.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) register.getScene().getWindow();
                        stage.close();
                    }
                });

                TextField name = new TextField();
                TextField pass = new TextField();
                TextField confirmPass = new TextField();

                name.setLayoutX(160);
                name.setLayoutY(50);
                pass.setLayoutX(160);
                pass.setLayoutY(100);
                confirmPass.setLayoutX(160);
                confirmPass.setLayoutY(150);

                secondaryLayout.getChildren().add(register);
                secondaryLayout.getChildren().add(name);
                secondaryLayout.getChildren().add(pass);
                secondaryLayout.getChildren().add(confirmPass);


                newWindow.show();
            }
        });



        TextField login = new TextField();
        TextField pass = new TextField();

        pass.setLayoutX(100);
        pass.setLayoutY(100);
        login.setLayoutX(100);
        login.setLayoutY(50);

        Pane root = new Pane();

        btn.setLayoutX(125);
        btn.setLayoutY(200);

        root.getChildren().add(btn);
        root.getChildren().add(reg);
        root.getChildren().add(pass);
        root.getChildren().add(login);



        primaryStage.setScene(new Scene(root, 383, 353));
        primaryStage.show();


    }
}
