package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Table.tableExtraditionReturn;
import sample.Table.tableOne;
import sample.Table.tableReader;
import sample.connect.Const;
import sample.connect.DatabaseHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerExtraditionReturn1 {

    @FXML
    private Button backButton;

    @FXML
    private Tab readersButton;

    @FXML
    private TableView<tableReader> tableReaders1;

    @FXML
    private TableColumn<tableReader, String> columnID1;

    @FXML
    private TableColumn<tableReader, String> columnSurname1;

    @FXML
    private TableColumn<tableReader, String> columnName1;

    @FXML
    private TableColumn<tableReader, String> columnPatronymic1;

    @FXML
    private TableColumn<tableReader, String> columnTicket1;

    @FXML
    private Tab booksButton;

    @FXML
    private TableView<tableOne> tableBooks;

    @FXML
    private TableColumn<tableOne, String> columnId;

    @FXML
    private TableColumn<tableOne, String> columnName;

    @FXML
    private TableColumn<tableOne, String> columnSurname;

    @FXML
    private TableColumn<tableOne, String> columnNameA;

    @FXML
    private TableColumn<tableOne, String> columnPatr;

    @FXML
    private Tab listButton;

    @FXML
    private TableView<tableExtraditionReturn> resultTable;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnID3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnSurname3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnName3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnPatronymic3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnNumber3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnNameBook3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnFIO3;

    @FXML
    private TableColumn<tableExtraditionReturn, String> columnIdBook3;

    @FXML
    private Button retBooksButton;

    @FXML
    private Button exBooksButton;

    @FXML
    private Button updateButton;

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
        columnID1.setCellValueFactory(new PropertyValueFactory<tableReader,String>("id_r"));
        columnName1.setCellValueFactory(new PropertyValueFactory<tableReader,String>("name"));
        columnSurname1.setCellValueFactory(new PropertyValueFactory<tableReader,String>("surname"));
        columnPatronymic1.setCellValueFactory(new PropertyValueFactory<tableReader,String>("patronymic"));
        columnTicket1.setCellValueFactory(new PropertyValueFactory<tableReader,String>("num_ticket"));
        tableReaders1.setItems(list);

        ObservableList<tableOne> list1 = FXCollections.observableArrayList();

        ResultSet result1 = dbHandler.choiceBooks1();

        while(result1.next()){

            tableOne tO = new tableOne(result1.getString(Const.INSTENCES_ID_I), result1.getString(Const.BOOKS_NAME),result1.getString(Const.AUTHOR_SURNAME),
                    result1.getString("NA"),result1.getString(Const.AUTHOR_PATRONYMIC));
            list1.add(tO);

        }

        columnId.setCellValueFactory(new PropertyValueFactory<tableOne,String>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<tableOne,String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<tableOne,String>("surname"));
        columnNameA.setCellValueFactory(new PropertyValueFactory<tableOne,String>("nameA"));
        columnPatr.setCellValueFactory(new PropertyValueFactory<tableOne,String>("patr"));
        tableBooks.setItems(list1);

        updateButton.setOnAction(event -> {
        ObservableList<tableExtraditionReturn> list2 = FXCollections.observableArrayList();

        ResultSet result2 = dbHandler.getExRet();

        while(true){
            try {
                if (!result2.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            tableExtraditionReturn tER = null;
            try {
                tER = new tableExtraditionReturn(result2.getString(Const.READER_ID_R), result2.getString(Const.READER_SURNAME)
                        , result2.getString(Const.READER_NAME), result2.getString(Const.READER_PATRONYMIC)
                        , result2.getString(Const.READER_NUM_TICKET), result2.getString("idBook"), result2.getString("bookName")
                        , result2.getString("FIO"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            list2.add(tER);

        }
        columnID3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("id_r"));
        columnSurname3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("surname"));
        columnName3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("name"));
        columnPatronymic3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("patronymic"));
        columnNumber3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("numberTicket"));
        columnIdBook3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("id_b"));
        columnNameBook3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("nameBook"));
        columnFIO3.setCellValueFactory(new PropertyValueFactory<tableExtraditionReturn,String>("FIO"));
        resultTable.setItems(list2);
        });

        exBooksButton.setOnAction(event -> {
            tableReader tr = tableReaders1.getSelectionModel().getSelectedItem();
            tableOne to = tableBooks.getSelectionModel().getSelectedItem();
                dbHandler.exBook1(to.id);
                dbHandler.exBook2(to.id, tr.getId_r());
            System.out.println(tr.getId_r() + " " + to.id);

        });

        retBooksButton.setOnAction(event -> {
            tableReader tr = tableReaders1.getSelectionModel().getSelectedItem();
            tableOne to = tableBooks.getSelectionModel().getSelectedItem();
                dbHandler.retBook1(to.id, tr.getId_r());
                dbHandler.retBook2(to.id);
            System.out.println(tr.getId_r()+ " " + to.id);

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