package sample.UserInterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.dialog.FontSelectorDialog;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import sample.Dictionaryy;
import sample.History;

public class mainController implements Initializable {
    /*----------- DECLARE THE VARIABLES -----------*/
    @FXML
    private StackPane root;
    @FXML
    private TextField wordInput;
    @FXML
    private VBox defOutput;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label wordName;
    @FXML
    private JFXTextArea area;
    @FXML
    private Label dateTime;

    private Dictionaryy dictionary = new Dictionaryy();
    private History history = new History();

    /*---------------------- Menu items ----------------------*/
    // Change fonts
    @FXML
    void fontAction() {
        // default css code for each characteristics of the text
        String textFont = "-fx-font-family: ";
        String textSize = "-fx-font-size: ";
        String textStyle = "";

        // Create and take the input from the Font dialog
        Dialog<Font> fontSelector = new FontSelectorDialog(null);
        Optional<Font> result = fontSelector.showAndWait();

        // add changes to the default CSS code above based on the users
        if (result.isPresent()) {
            Font newFont = result.get();
            textFont += "\"" + newFont.getFamily() + "\";";
            textSize += newFont.getSize() + "px;";

            // some basics CSS font characteristics
            String style = newFont.getStyle();
            String style_italic = "-fx-font-style: italic;";
            String style_regular = "-fx-font-weight: normal ;";
            String style_bold = "-fx-font-weight: bold;";
            switch (style) {
                case "Bold Italic":
                    textStyle += style_bold + "\n" + style_italic;
                case "Italic":
                    textStyle += style_italic;
                case "Regular":
                    textStyle += style_regular;
                case "Regular Italic":
                    textStyle += style_italic;
                default:
                    textStyle += style_bold;
            }

            // Add all characteristic to a single string
            String finalText = textFont + "\n" + textSize;
            finalText += "\n" + textStyle;
            // Display options and set that options to the text
            defOutput.setStyle(finalText);
        }
    }

    // Change theme : remove the old one, then add the new one
    @FXML
    void darkThemeAction() {
        root.getStylesheets().remove("sample/UserInterface/light_theme.css");
        root.getStylesheets().add("sample/UserInterface/dark_theme.css");
    }
    @FXML
    void lightThemeAction() {
        root.getStylesheets().remove("sample/UserInterface/dark_theme.css");
        root.getStylesheets().add("sample/UserInterface/light_theme.css");
    }

    // Function to "add bookmark"
    @FXML
    void addBookmarkAction(){
        String message=null;
        ObservableList<String> items;
        items=listView.getSelectionModel().getSelectedItems();
        for(String m:items){//this loop through items and stores the value as m
            message += m +"\n";
            if(m!=null) {
                message=message.replaceAll("null","");
                history.setBookmark(m);
            }
        }
    }

    // Display "my bookmark" to a table view in new window
    @FXML
    void myBookmarkAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDictionaryyy.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e){
            System.out.println("Can't load");
        }
    }

    @FXML
    void addWordAction(ActionEvent event){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("This feature will appear in the next update. Thanks!"));

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Done");
        button.setOnAction(e ->dialog.close());
        content.setActions(button);
        dialog.show();
    }

    // open online version
    @FXML
    void openOnlineAction() {
        try {
            Desktop.getDesktop().browse(new URI(
                    "https://xlinux.nist.gov/dads/"));
        } catch (IOException| URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void checkUpdateAction() {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("You are up to date!"));
        content.setBody(new Text("Version: ADS Dictionary v1.0\n\n"+
                "New feature:\n"+
                "- Add shortcuts Alt+M, Alt+S, Alt+T, Alt+H to open menu\n"+
                "- Add feature \"Add bookmark\" and \"My bookmark\", so now the users can save some words\n"+
                "- Add system time on the bottom right conner\n" +
                "- Add new feature: The users can select the text and copy using right mouse"));

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("Done");
        button.setOnAction(e ->dialog.close());
        content.setActions(button);
        dialog.show();
    }

    @FXML
    void aboutAction() {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Developer Team: The Eleventh"));
            content.setBody(new Text("\t* Project topic: Algorithms and Data structures dictionary\n" +
                    "\t* Instructor: Dr. Nghiem Thi Phuong\n"+
                    "\t* Leader: Truong Si Thi Vu\n\n"+
                    "\t* Team members:\n" +
                    "1. Dinh Nhu Minh Phuong - Front end developer\n" +
                    "2. Praise Oketola - Back end developer\n"+
                    "3. Ta Duc Anh - Data analyst\n" +
                    "4. Phan Manh Tung - Data analyst\n" +
                    "5. Truong Si Thi Vu - Code tester\n"));

            JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);

            JFXButton button = new JFXButton("Done");
            button.setOnAction(e ->dialog.close());
            content.setActions(button);
            dialog.show();
        }

    @FXML
    void donateAction() {
        try {
            Desktop.getDesktop().browse(new URI("https://steamcommunity.com/market/search?appid=578080"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    /*---------------------- MAIN MENU ----------------------*/
    // click the button to summit input from text field
    @FXML
    void searchButtonAction() {
        String definition=dictionary.Search(wordInput.getText());
        String translation=dictionary.Translate(wordInput.getText());
        area.setWrapText(true);
        area.setText(definition+"\n\n\n\nTranslation: "+ translation);

        String name = wordInput.getText().substring(0,1).toUpperCase() + wordInput.getText().substring(1).toLowerCase();
        wordName.setText(name);
        listView.getItems().addAll(wordInput.getText());
    }

    private void displayTime(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /*Read more button action*/
    @FXML
    void go_to_link(){
        String key= wordName.getText().toLowerCase();
        String link= dictionary.get_key_link(key);
        link = link.replaceAll("^\"|\"S","");
        File file = new File(link);

        try{
            if(!Desktop.isDesktopSupported()){
                System.out.println("Desktop is not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())
                desktop.edit(file);
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }

    private String[] possibleWord=  Stream.concat(Arrays.stream(dictionary.getKeyArray()), Arrays.stream(dictionary.getKeyArrayViet()))
            .toArray(String[]::new);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // initialize words to text for user input
        TextFields.bindAutoCompletion(wordInput, possibleWord);
        // uneditable the text area
        area.setEditable(false);
        // initialize date and time
        displayTime();
        // initialize the default light theme
        lightThemeAction();
    }
}
