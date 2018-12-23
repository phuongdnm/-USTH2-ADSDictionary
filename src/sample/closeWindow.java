package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class closeWindow {
    private static boolean answer; // return the value to the main window

    public static boolean display() {
        Stage window = new Stage();

        // prevent users to do any stuffs with the previous window
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Exit");
        window.setMinWidth(250);

        // create a new label and a button to close the pop up window
        Label label = new Label();
        label.setText("Do you want to exit?");

        // create 2 buttons to choose: yes or no
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        // add functions to the 2 buttons above
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e ->{
            answer = false;
            window.close();
        });
        Label fun = new Label("I challenge you to touch the dictionary screen :))");

        // create the layout for the pop up window
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 5, 5, 5));
        HBox optionBox = new HBox(10);
        optionBox.getChildren().addAll(yesButton,noButton);
        optionBox.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().addAll(label, optionBox, fun);
        layout.setAlignment(Pos.CENTER); // format all the stuff to the middle

        // create the scene and show it
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }
}
