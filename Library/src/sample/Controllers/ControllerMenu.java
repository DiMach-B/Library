package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMenu {


    @FXML
    private Button availBooksButton;

    @FXML
    private Button extradRetButton;

    @FXML
    private Button addReaderButton;

    @FXML
    private Button holderSearchButton;

    @FXML
    private Button searchMaskButton;

    @FXML
    private Button reportsButton;



    @FXML
    void initialize() {
        availBooksButton.setOnAction(event -> {
            availBooksButton.getScene().getWindow().hide();
            openNewScene("../FXML/AvailBooks.fxml");
        });

        addReaderButton.setOnAction(event -> {
            addReaderButton.getScene().getWindow().hide();
            openNewScene("../FXML/AddReader.fxml");
        });

        extradRetButton.setOnAction(event -> {
            extradRetButton.getScene().getWindow().hide();
            openNewScene("../FXML/ExtraditionReturn.fxml");
        });

        holderSearchButton.setOnAction(event -> {
            holderSearchButton.getScene().getWindow().hide();
            openNewScene("../FXML/HolderSearch.fxml");
        });

        searchMaskButton.setOnAction(event -> {
            searchMaskButton.getScene().getWindow().hide();
            openNewScene("../FXML/SearchMask.fxml");
        });

        reportsButton.setOnAction(event -> {
            reportsButton.getScene().getWindow().hide();
            openNewScene("../FXML/Reports.fxml");
        });
    }

    public void openNewScene(String window){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

