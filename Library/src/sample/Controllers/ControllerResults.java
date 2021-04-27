package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Table.tableOne;
import sample.Table.tableResults;
import sample.connect.Const;
import sample.connect.DatabaseHandler;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerResults {

    @FXML
    private TableView<tableResults> tableResults;

    @FXML
    private TableColumn<tableResults, Integer> columnID;

    @FXML
    private TableColumn<tableResults, String> columnName;

    @FXML
    private TableColumn<tableResults, String> columnFIO;

    @FXML
    private TableColumn<tableResults, String> columnCondition;

    @FXML
    private TableColumn<tableResults, String> columnDateGet;

    @FXML
    private TableColumn<tableResults, String> columnDateRet;

    @FXML
    private Button backButton;

    @FXML
    void initialize() throws SQLException {

        ObservableList<tableResults> list = FXCollections.observableArrayList();

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getResult();

        while(result.next()){

            tableResults tableResult = new tableResults(result.getString(Const.INSTENCES_ID_I),result.getString(Const.BOOKS_NAME),
                    result.getString("FIO"),result.getString(Const.INSTENCES_CONDITION),result.getString(Const.DISTRIBUTION_DATE_GET), result.getString(Const.DISTRIBUTION_DATE_RETURN));
            list.add(tableResult);

        }
        columnID.setCellValueFactory(new PropertyValueFactory<tableResults,Integer>("id_d"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableResults,String>("name"));
        columnFIO.setCellValueFactory(new PropertyValueFactory<tableResults,String>("FIO"));
        columnCondition.setCellValueFactory(new PropertyValueFactory<tableResults,String>("condition"));
        columnDateGet.setCellValueFactory(new PropertyValueFactory<tableResults,String>("date_get"));
        columnDateRet.setCellValueFactory(new PropertyValueFactory<tableResults,String>("date_return"));
        tableResults.setItems(list);


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
}