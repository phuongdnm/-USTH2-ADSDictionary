package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UserInterface/mainWindow.fxml"));
        primaryStage.setTitle("ADS Dictionary v1.0");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            boolean check = closeWindow.display();
            if(check)
                primaryStage.close();
        }); // close the application
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        // set icon to the window
        Image icon = new Image("sample/dictionary.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
        /* Start putting your code from here. Please don't touch any stuff above */
    }
}
