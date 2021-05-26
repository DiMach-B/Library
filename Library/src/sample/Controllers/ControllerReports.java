package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerReports {

    @FXML
    private Button resultsButton;

    @FXML
    private Button cardButton;

    @FXML
    private Button topBooksButton;

    @FXML
    private Button formBooksButton;

    @FXML
    private Button readersButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        cardButton.setOnAction(event -> {
            cardButton.getScene().getWindow().hide();
            openNewScene("../FXML/Card.fxml");
        });

        topBooksButton.setOnAction(event -> {
            topBooksButton.getScene().getWindow().hide();
            openNewScene("../FXML/TopBooks.fxml");
        });

        formBooksButton.setOnAction(event -> {
            formBooksButton.getScene().getWindow().hide();
            openNewScene("../FXML/FormBooks.fxml");
        });

        resultsButton.setOnAction(event -> {
            resultsButton.getScene().getWindow().hide();
            openNewScene("../FXML/Results.fxml");
        });

        readersButton.setOnAction(event -> {
            readersButton.getScene().getWindow().hide();
            openNewScene("../FXML/Readers.fxml");
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            openNewScene("../FXML/Menu.fxml");
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

