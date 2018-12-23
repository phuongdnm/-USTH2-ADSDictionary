package sample.UserInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import sample.History;

import java.net.URL;
import java.util.ResourceBundle;

public class myDictionary_Controller implements Initializable {
    History history = new History();
    @FXML
    private StackPane pane;
    @FXML
    private TableView<myDictionary_words> tableView;
    @FXML
    private TableColumn<myDictionary_words, String> wordsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordsColumn.setCellValueFactory(new PropertyValueFactory<>("words"));
        tableView.setItems(getMyDictionary_words());
    }

    public ObservableList<myDictionary_words> getMyDictionary_words() {
        ObservableList<myDictionary_words> products = FXCollections.observableArrayList();

        for (int i = 0; i < history.getBookmark().length; i++) {
            products.add(new myDictionary_words(history.getBookmark()[i]));
        }
        return products;
    }

    @FXML
    void DeleteBookmark() {
        ObservableList<myDictionary_words> productSelected, allproducts, wordsSelected;
        allproducts = tableView.getItems();
        productSelected = tableView.getSelectionModel().getSelectedItems();
        wordsSelected = tableView.getSelectionModel().getSelectedItems();
        for (myDictionary_words m : wordsSelected) {
            history.deleteBookmark(m.getWords());
        }
        productSelected.forEach(allproducts::remove);
    }
}
