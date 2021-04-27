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
import sample.Table.tableCard;
import sample.Table.tableTop;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.Chronology;

public class ControllerTopBooks {

    @FXML
    private Button backButton;

    @FXML
    private TableView<tableTop> tableTop;

    @FXML
    private TableColumn<tableTop, Integer> columnidBook;

    @FXML
    private TableColumn<tableTop, String> columnQuality;

    @FXML
    private TableColumn<tableTop, String> columnNameBook;

    @FXML
    private TableColumn<tableTop, String> columnFIO;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button seeButton;

    @FXML
    private Label labelInfoDate;


    @FXML
    void initialize() {

        seeButton.setOnAction(event -> {

            TextField sD =startDate.getEditor();
            String d1 = sD.getText();

            TextField eD =endDate.getEditor();
            String d2 = eD.getText();
            if (!d1.equals("") &&  !d2.equals("")) {
                labelInfoDate.setText("");
                try {
                    searchTop(d1, d2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else{
                labelInfoDate.setText("Вы заполнили не все поля!");
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

    public void searchTop(String startDate, String endDate) throws SQLException {

        ObservableList<tableTop> list = FXCollections.observableArrayList();
                DatabaseHandler dbHandler = new DatabaseHandler();
          ResultSet result = dbHandler.getTop(startDate, endDate);

          while(result.next()){

        tableTop tableT = new tableTop(result.getString(Const.BOOKS_ID_B), result.getString("MAXIMUM"),
                result.getString(Const.BOOKS_NAME), result.getString("FIO"));
        list.add(tableT);

                }
        columnidBook.setCellValueFactory(new PropertyValueFactory<tableTop, Integer>("id_b"));
        columnQuality.setCellValueFactory(new PropertyValueFactory<tableTop, String>("quality"));
        columnNameBook.setCellValueFactory(new PropertyValueFactory<tableTop, String>("name"));
        columnFIO.setCellValueFactory(new PropertyValueFactory<tableTop, String>("FIO"));
        tableTop.setItems(list);
    }
}

