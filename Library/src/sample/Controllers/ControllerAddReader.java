package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connect.DatabaseHandler;

import java.io.IOException;

public class ControllerAddReader {

    @FXML
    private TextField surnameReader_field;

    @FXML
    private TextField nameReader_field;

    @FXML
    private TextField patronymicReader_filed;

    @FXML
    private Button addReader;

    @FXML
    private Button backButton;

    @FXML
    private TextField email_field;

    @FXML
    private TextField telephone_field;


    @FXML
    private DatePicker dateBirth;

    @FXML
    private Label labelInfo;

    @FXML
    void initialize() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        addReader.setOnAction(event -> {

            TextField birth = dateBirth.getEditor();
            String readerBirth = birth.getText().trim();
            String readerTelephone = telephone_field.getText().trim();
            String readerEmail = email_field.getText().trim();
            String readerName = nameReader_field.getText().trim();
            String readerPatronymic = patronymicReader_filed.getText().trim();
            String readerSurname = surnameReader_field.getText().trim();

            if(!readerBirth.equals("") && ! readerTelephone.equals("") && !readerEmail.equals("") && !readerName.equals("")
                    && !readerPatronymic.equals("") && !readerSurname.equals("")) {
                dbHandler.addUser(readerBirth, readerTelephone, readerEmail,
                        readerName, readerPatronymic, readerSurname);
                labelInfo.setText("     Читатель добавлен");
            }
            else{
                labelInfo.setText("Вы заполнили не все поля!");
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
