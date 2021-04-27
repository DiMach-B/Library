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
import sample.Table.tableHolder;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerHolderSearch {

    @FXML
    private TextField idB_field;

    @FXML
    private Button searchReaderButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<tableHolder> tableReader;

    @FXML
    private TableColumn<tableHolder, String> columnIdReader;

    @FXML
    private TableColumn<tableHolder, String> columnFIOReader;

    @FXML
    private TableColumn<tableHolder, String> columnTicketReader;

    @FXML
    private TableColumn<tableHolder, String> columnTelephoneReader;

    @FXML
    private Label labelInfo;

    @FXML
    void initialize() {
        searchReaderButton.setOnAction(event -> {

            String idReader = idB_field.getText().trim();

            if (!idReader.equals("")) {
                try {
                    searchHolder(idReader);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelInfo.setText("");
            }
            else
                labelInfo.setText("Вы заполнили не все поля!");
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

    public void searchHolder(String idReader) throws SQLException {

        ObservableList<tableHolder> list = FXCollections.observableArrayList();
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getHolder(idReader);

        while (result.next()) {

            tableHolder tableR = new tableHolder(result.getString(Const.READER_ID_R), result.getString("FIO"),
                    result.getString(Const.READER_NUM_TICKET), result.getString(Const.READER_TELEPHONE));
            list.add(tableR);

        }
        columnIdReader.setCellValueFactory(new PropertyValueFactory<tableHolder, String>("id_r"));
        columnFIOReader.setCellValueFactory(new PropertyValueFactory<tableHolder, String>("FIO"));
        columnTicketReader.setCellValueFactory(new PropertyValueFactory<tableHolder, String>("ticket"));
        columnTelephoneReader.setCellValueFactory(new PropertyValueFactory<tableHolder, String>("telephone"));
        tableReader.setItems(list);
    }
}
