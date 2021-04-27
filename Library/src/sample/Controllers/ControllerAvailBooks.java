package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Table.tableOne;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerAvailBooks {

    @FXML
    private TextField name_field;

    @FXML
    private TextField patronymic_filed;

    @FXML
    private TextField nameBooks_field;

    @FXML
    private TextField surname_field;

    @FXML
    private Button searchBooks;

    @FXML
    private Button backButton;

    @FXML
    private TableView<tableOne> tableBooks;

    @FXML
    private TableColumn<tableOne, String> columnName;

    @FXML
    private TableColumn<tableOne, String> columnSurname;

    @FXML
    private TableColumn<tableOne, String> columnNameA;

    @FXML
    private TableColumn<tableOne, String> columnPatr;

    @FXML
    private TableColumn<tableOne, String> columnId;

    @FXML
    private Label InfoLabel;

    @FXML
    void initialize() {

        searchBooks.setOnAction(event -> {
            String  nameAuthor = name_field.getText().trim();
            String  patronymicAuthor = patronymic_filed.getText().trim();
            String  surnameAuthor = surname_field.getText().trim();
            String  nameBook = nameBooks_field.getText().trim();


            
            if (!nameAuthor.equals("") && !patronymicAuthor.equals("") && !nameBook.equals("") && !surnameAuthor.equals("") ){
                try {
                    InfoLabel.setText("");
                    search(nameBook, nameAuthor,  surnameAuthor, patronymicAuthor);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
   }
            else
                InfoLabel.setText("Вы заполнили не все поля!");
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

    private void search(String nameBook, String nameAuthor, String surnameAuthor, String patronymicAuthor) throws SQLException {

        ObservableList<tableOne> list = FXCollections.observableArrayList();

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.choiceBooks(nameBook, nameAuthor, surnameAuthor, patronymicAuthor);

        while(result.next()){

            tableOne tableOne = new tableOne(result.getString(Const.INSTENCES_ID_I), result.getString(Const.BOOKS_NAME),result.getString(Const.AUTHOR_SURNAME),
                    result.getString("NA"),result.getString(Const.AUTHOR_PATRONYMIC));
            list.add(tableOne);

       }
        columnId.setCellValueFactory(new PropertyValueFactory<tableOne,String>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableOne,String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<tableOne,String>("surname"));
        columnNameA.setCellValueFactory(new PropertyValueFactory<tableOne,String>("nameA"));
        columnPatr.setCellValueFactory(new PropertyValueFactory<tableOne,String>("patr"));
        tableBooks.setItems(list);
    }
}