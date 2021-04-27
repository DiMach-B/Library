package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Table.tableCard;
import sample.Table.tableFormBooks;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerFormBooks {

    @FXML
    private AnchorPane back;

    @FXML
    private Button backButton;

    @FXML
    private TextField idBook_field;

    @FXML
    private TableView<tableFormBooks> tableFormBook;

    @FXML
    private TableColumn<tableFormBooks, String> columnId;

    @FXML
    private TableColumn<tableFormBooks, String> columnName;

    @FXML
    private TableColumn<tableFormBooks, String> columnFIO;

    @FXML
    private TableColumn<tableFormBooks, String> columnCipher;

    @FXML
    private TableColumn<tableFormBooks, String> columnPublish;

    @FXML
    private TableColumn<tableFormBooks, String> columnTopic;

    @FXML
    private TableColumn<tableFormBooks, String> columnDateP;

    @FXML
    private TableColumn<tableFormBooks, String> columnQuality;

    @FXML
    private TableColumn<tableFormBooks, String> columnGet;

    @FXML
    private Button searchButton;

    @FXML
    private Label labelInfoBook;

    @FXML
    void initialize() {


        searchButton.setOnAction(event -> {

            String  idBook = idBook_field.getText().trim();

            if(!idBook.equals("")) {
                labelInfoBook.setText("");
                try {
                    getFormBook(idBook);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else{
                labelInfoBook.setText("Вы заполнили не все поля!");
            }
        });


        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../FXML/Reports.fxml"));

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

    public void getFormBook(String idBook) throws SQLException {

        ObservableList<tableFormBooks> list = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getFormBook(idBook);

        while(result.next()){

            tableFormBooks tableFB = new tableFormBooks(result.getString(Const.BOOKS_ID_B), result.getString(Const.BOOKS_NAME), result.getString("FIO"),
                    result.getString(Const.BOOKS_CIPHER), result.getString(Const.BOOKS_PUBLISH), result.getString(Const.BOOKS_TOPIC), result.getString(Const.BOOKS_DATE_P),
                    result.getString(Const.BOOKS_QUALITY), result.getString("GET"));
            list.add(tableFB);

        }
        columnId.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("id_i"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("name"));
        columnFIO.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("FIO"));
        columnCipher.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("cipher"));
        columnPublish.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("publish"));
        columnTopic.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("topic"));
        columnDateP.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("date_p"));
        columnQuality.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("quality"));
        columnGet.setCellValueFactory(new PropertyValueFactory<tableFormBooks, String>("get"));
        tableFormBook.setItems(list);
    }
}