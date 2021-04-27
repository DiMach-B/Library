package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connect.DatabaseHandler;

import java.io.IOException;

public class ControllerExtraditionReturn {

    @FXML
    private TextField exIdR_field;

    @FXML
    private TextField retIdI_filed;

    @FXML
    private TextField retIdR_filed;

    @FXML
    private TextField exIdI_field;

    @FXML
    private Button retBooksButton;

    @FXML
    private Button backButton;

    @FXML
    private Button exBooksButton;

    @FXML
    private Label labelInfo1;

    @FXML
    private Label labelInfo2;


    @FXML
    void initialize() {



        exBooksButton.setOnAction(event -> {

            String idI = exIdI_field.getText().trim();
            String idR = exIdR_field.getText().trim();

            if (!idI.equals("") && !idR.equals("")) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                dbHandler.exBook1(idI);
                dbHandler.exBook2(idI, idR);
                labelInfo1.setText("     Экземпляр выдан");
            }
            else{
                labelInfo1.setText("Вы заполнили не все поля!");
            }
        });

        retBooksButton.setOnAction(event -> {

            String idI = retIdI_filed.getText().trim();
            String idR = retIdR_filed.getText().trim();
            if (!idI.equals("") && !idR.equals("")) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                dbHandler.retBook1(idI, idR);
                dbHandler.retBook2(idI);
                labelInfo2.setText("  Экземпляр возращён");
            }
            else{
                labelInfo2.setText("Вы заполнили не все поля!");
            }
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../FXML/Menu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}