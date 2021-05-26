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
import sample.Table.tableReader;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerReaders {

    @FXML
    private Button backButton;

    @FXML
    private TableView<tableReader> tableReaders;

    @FXML
    private TableColumn<tableReader, String> columnID;

    @FXML
    private TableColumn<tableReader, String> columnSurname;

    @FXML
    private TableColumn<tableReader, String> columnName;

    @FXML
    private TableColumn<tableReader, String> columnPatronymic;

    @FXML
    private TableColumn<tableReader, String> columnTelephone;

    @FXML
    private TableColumn<tableReader, String> columnEmail;

    @FXML
    private TableColumn<tableReader, String> columnTicket;

    @FXML
    private TableColumn<tableReader, String> columnBirth;

    @FXML
    void initialize() throws SQLException {

        ObservableList<tableReader> list = FXCollections.observableArrayList();

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getReaders();

        while(result.next()){

            tableReader tR = new tableReader(result.getString(Const.READER_ID_R),result.getString(Const.READER_NAME),
                    result.getString(Const.READER_SURNAME),result.getString(Const.READER_PATRONYMIC)
                    ,result.getString(Const.READER_TELEPHONE), result.getString(Const.READER_EMAIL)
                    ,result.getString(Const.READER_NUM_TICKET), result.getString(Const.READER_DATE_BIRTHDAY));
            list.add(tR);

        }
        columnID.setCellValueFactory(new PropertyValueFactory<tableReader,String>("id_r"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableReader,String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<tableReader,String>("surname"));
        columnPatronymic.setCellValueFactory(new PropertyValueFactory<tableReader,String>("patronymic"));
        columnTelephone.setCellValueFactory(new PropertyValueFactory<tableReader,String>("telephone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<tableReader,String>("email"));
        columnTicket.setCellValueFactory(new PropertyValueFactory<tableReader,String>("num_ticket"));
        columnBirth.setCellValueFactory(new PropertyValueFactory<tableReader,String>("date_birthday"));
        tableReaders.setItems(list);




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
