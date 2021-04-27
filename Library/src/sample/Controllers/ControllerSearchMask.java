package sample.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Table.tableMask;
import sample.Table.tableTop;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerSearchMask {

    @FXML
    private TextField topicBook_field;

    @FXML
    private TextField publishBook_field;

    @FXML
    private TextField nameAuthor_field;

    @FXML
    private TextField nameBook_field;

    @FXML
    private Button searchBookButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField surnameAuthor_field;

    @FXML
    private TableView<tableMask> tableMaskBook;

    @FXML
    private TableColumn<tableMask, String> columnName;

    @FXML
    private TableColumn<tableMask, String> columnTopic;

    @FXML
    private TableColumn<tableMask, String> columnPublish;

    @FXML
    private TableColumn<tableMask, String> columnAuthorName;

    @FXML
    private TableColumn<tableMask, String> columnAuthorPatronymic;

    @FXML
    private TableColumn<tableMask, String> columnAuthorSurname;

    @FXML
    private TextField patronymicAuthor_field;

    @FXML
    private Label labelInfoDate;

    @FXML
    void initialize() {

        searchBookButton.setOnAction(event -> {
            String bookName = nameBook_field.getText().trim();
            String bookTopic = topicBook_field.getText().trim();
            String bookPublish = publishBook_field.getText().trim();
            String nameAuthor = nameAuthor_field.getText().trim();
            String patronymicAuthor = patronymicAuthor_field.getText().trim();
            String surnameAuthor = surnameAuthor_field.getText().trim();

            if(bookName.equals("") && bookTopic.equals("") && bookPublish.equals("") && nameAuthor.equals("")
                    && patronymicAuthor.equals("") && surnameAuthor.equals("")) {
                labelInfoDate.setText("Укажитете хотя бы один параметр!");
            }
            else{
                try {
                    searchMask(bookName, bookTopic, bookPublish, nameAuthor
                            , patronymicAuthor, surnameAuthor);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    labelInfoDate.setText("");
                }
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

    public void searchMask(String bookName, String bookTopic, String bookPublish, String bookAuthorName
            , String bookPatronymic, String bookSurname) throws SQLException {


        ObservableList<tableMask> list = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getMask(bookName, bookTopic, bookPublish, bookAuthorName
                , bookPatronymic, bookSurname);

        while (result.next()) {

            tableMask tableT = new tableMask(result.getString(Const.BOOKS_NAME), result.getString(Const.BOOKS_TOPIC),
                    result.getString(Const.BOOKS_PUBLISH), result.getString(Const.AUTHOR_NAME), result.getString(Const.AUTHOR_PATRONYMIC)
                    , result.getString(Const.AUTHOR_SURNAME));
            list.add(tableT);

        }
        columnName.setCellValueFactory(new PropertyValueFactory<tableMask, String>("name"));
        columnTopic.setCellValueFactory(new PropertyValueFactory<tableMask, String>("topic"));
        columnPublish.setCellValueFactory(new PropertyValueFactory<tableMask, String>("publish"));
        columnAuthorName.setCellValueFactory(new PropertyValueFactory<tableMask, String>("authorName"));
        columnAuthorSurname.setCellValueFactory(new PropertyValueFactory<tableMask, String>("authorPatronymic"));
        columnAuthorPatronymic.setCellValueFactory(new PropertyValueFactory<tableMask, String>("authorSurname"));

        tableMaskBook.setItems(list);
    }
}
