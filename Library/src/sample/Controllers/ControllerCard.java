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
import sample.Table.tableResults;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerCard {

    @FXML
    private AnchorPane back;

    @FXML
    private Button backButton;

    @FXML
    private TextField idReader_field;

    @FXML
    private TableView<tableCard> tableCard;

    @FXML
    private TableColumn<tableCard, Integer> columnId;

    @FXML
    private TableColumn<tableCard, String> columnName;

    @FXML
    private TableColumn<tableCard, String> columnFIO;

    @FXML
    private TableColumn<tableCard, String> columnDateGet;

    @FXML
    private TableColumn<tableCard, String> columnDateRet;

    @FXML
    private Button searchButton;

    @FXML
    private Label labelInfo;

    @FXML
    void initialize() {

        searchButton.setOnAction(event -> {

            String  idReader = idReader_field.getText().trim();

            if (!idReader.equals("")) {
                labelInfo.setText("");
                try {
                    searchCard(idReader);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else {
                labelInfo.setText("Вы заполнили не все поля!");
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

    public void searchCard(String idReader) throws SQLException {

        ObservableList<tableCard> list = FXCollections.observableArrayList();
                DatabaseHandler dbHandler = new DatabaseHandler();
          ResultSet result = dbHandler.getCard(idReader);

          while(result.next()){

        tableCard tableC = new tableCard(result.getString(Const.INSTENCES_ID_I), result.getString(Const.BOOKS_NAME), result.getString("FIO"),
                result.getString(Const.DISTRIBUTION_DATE_GET), result.getString(Const.DISTRIBUTION_DATE_RETURN));
        list.add(tableC);

                }
        columnId.setCellValueFactory(new PropertyValueFactory<tableCard, Integer>("id_i"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableCard, String>("name"));
        columnFIO.setCellValueFactory(new PropertyValueFactory<tableCard, String>("FIO"));
        columnDateGet.setCellValueFactory(new PropertyValueFactory<tableCard, String>("date_get"));
        columnDateRet.setCellValueFactory(new PropertyValueFactory<tableCard, String>("date_return"));
        tableCard.setItems(list);
    }
}