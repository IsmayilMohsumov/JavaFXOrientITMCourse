package az.com.course.controller;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;
import az.com.course.service.ServiceImplement;
import az.com.course.service.ServiceInterface;
import az.com.course.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class showUsersController implements Initializable {
    @FXML
    private TableView<PersonDTO> userTableView;

    @FXML
    private TextField searchByPin;
    @FXML
    private TableColumn<PersonDTO,Integer> tableID;
    @FXML
    private TableColumn<PersonDTO,String> tableName;
    @FXML
    private TableColumn<PersonDTO,String> tableSurname;
    @FXML
    private TableColumn<PersonDTO,String> tableEmail;
    @FXML
    private TableColumn<PersonDTO,String> tablePin;
    @FXML
    private TableColumn<PersonDTO,String> tablePosition;

    @FXML // ADV26
    private TextField searchByName;
    @FXML // ADV26
    private TextField searchBySurname;
    @FXML // ADV26
    private TextField searchByEmail;
    @FXML // ADV26
    private ComboBox<DictionaryDTO> searchByPosition;



    //service kece bilmek ucun obyektini yaradiriq
    private ServiceInterface serviceInterface = new ServiceImplement();

    @Override//table in icine deyerleri gonderirik .
    public void initialize(URL location, ResourceBundle resources) {
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tablePin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tablePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
           //* userTableView.setItems(personsTableShow()); (silindi*)
            //setItems methodu sadece Observable list qebul etdiyi ucun onu Observable liste otururuk;
        try {
            personsTableShow(PersonDTOS());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /////////////////////////////////

        //methoddan gelen deyerleri Observable liste yigiriq //ADV26(10:06)

    }

    //comboBoxa deyerleri getiren metodumuz
    public List<DictionaryDTO> comboDatas (String key) throws SQLException {//key yuxarda ObservableListden gelir
        return serviceInterface.comboDatasByKey(key);
    }

    //k0od tekrarina yol vermemek ucun Observable listin yaranma hissesini de methoda atdiq bu methodu cagirdigda observable listi yaradacaq ve
    // userTable.SET edecek ;
    private void personsTableShow(List<PersonDTO> createdList){//createdList yeni herkes oz yaratdiqi listi gonderir ve biz onu observable liste aliriq
        ObservableList<PersonDTO> personObservable = null;
        try {
            //Listimizi observableye menimsetdik ve onu asaghida
            personObservable = FXCollections.observableArrayList(createdList);
            //--- yeni burada table mize set edeceyik;
            userTableView.setItems(personObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //burdan gelen listi observablede cagiracayiq burdan gelen databaseden goturub gelecek;
    public List<PersonDTO> PersonDTOS () throws SQLException {
        return serviceInterface.personDTOS();

    }


    @FXML//bu methodda getSelectionModel().getSelectedItem() yeni modelden secilmish Item i geri qaytarsin personDto nun icine
    public void editUser(MouseEvent mouseEvent){
        PersonDTO personDTO = userTableView.getSelectionModel().getSelectedItem();

        Stage primaryStage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();//fxml loaderin obyektini yaradirig metodlarini istifade etmek uucun
        fxmlLoader.setLocation(getClass().getResource("/fxml/uptadeUser.fxml"));//burada setLocation yeni hasni fxml load olunsun acilsin
        try {
            root = fxmlLoader.load();
          } catch (Exception e) {
            e.printStackTrace();
        }
            uptadeUserController uptadeUser=fxmlLoader.getController(); // getController icinde obyekt saxlayan bir metoddur onu obyekte menimsedirik . new verse idik eyni netice alinmayacaqdi
            uptadeUser.setFields(personDTO);//metodun icinde deyerleri gonderirik
        primaryStage.setTitle("Edit User");//appin ustunde yazan ad hissesi
        primaryStage.getIcons().add(new Image("/icons/pngUFM.jpeg"));
        primaryStage.setScene(new Scene(root, 708, 400));//umumi acilmaq olcusunu buradan veririrk
        primaryStage.show();
        Utility.hideWindow(mouseEvent);
    }


    @FXML//bu methodu TextField de (Searchbypin)KeyReleased de cagiririq yeni nevaxdki orada herhansi birsey yazildiqda onu verecek
    public void searchByPin(Event event)  {
        if (event instanceof KeyEvent){//instanceof yeni - icinde varmi yeni keyEvent Eventin icinde varmi
            //            System.out.println(searchByPin.getText());
            String textByPin = searchByPin.getText();
            try {
                List <PersonDTO> users =serviceInterface.findUsersByPin(textByPin);
                personsTableShow(users);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //ders26(ADV)(09:15)// qisaAd== ADV26
  //advancedSearch Hissemizin methodunu yaradiriq ve bu methodu oradaki buttonun(yeni FXML in icinde)onActionunda cagiririq

}
