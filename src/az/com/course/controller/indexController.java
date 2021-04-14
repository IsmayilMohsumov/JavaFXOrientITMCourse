package az.com.course.controller;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;
import az.com.course.service.ServiceImplement;
import az.com.course.service.ServiceInterface;
import az.com.course.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class indexController implements Initializable {

    //deyerlerin axish grafasi :
    //FRAME --> CONTROLLER (indexController) --> SERVICE --> DAO -- VE DEYERLERI DB YE YIGIR
    //DB --> DAO --> SERVICE --> CONTROLLER --> FRONT(FRAME , VIEW);
    private ServiceInterface serviceInterface = new ServiceImplement();//deyerleri siralama ile gonderdiyimiz ucun //CONTROLLERDEN SONRA SERVICE;
    private Utility utility = new Utility();

    @FXML
    private Label requestMessage;
    @FXML
    private TextField userName;
    @FXML
    private TextField userSurname;
    @FXML
    private TextField userPin;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button buttonDaxilEt;
    @FXML
    private ComboBox<DictionaryDTO> userPersonType;

    @Override//comboBoxun icini doldurmaq.
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<DictionaryDTO> personTypes = FXCollections.observableArrayList(comboDatas("personType"));//hemcinin lesson type vere bilerik
            userPersonType.setItems(personTypes);//comboBoxumuz // bu metodda ancaq observable list aldigi ucun
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //comboBoxa deyerleri getiren metodumuz
    public List<DictionaryDTO> comboDatas (String key) throws SQLException {//key yuxarda ObservableListden gelir
        return serviceInterface.comboDatasByKey(key);
    }

    @FXML//textfieldlerin icindekideyerleri evvelce personDto nun icine yigib sonra bir bir database e gondermek
    public void addUser (ActionEvent actionEvent){
        String name = userName.getText();
        String surname = userSurname.getText();
        String pin = userPin.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        DictionaryDTO userPersonType = this.userPersonType.getSelectionModel().getSelectedItem();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setSurname(surname);
        personDTO.setPin(pin);
        personDTO.setEmail(email);
        personDTO.setPassword(password);
//        personDTO.setId(7);
        personDTO.setUserPersonType(userPersonType);
        try {
            serviceInterface.addPerson(personDTO);
            requestMessage.setText("Succesfully Added");
        } catch (SQLException e) {
            e.printStackTrace();
            requestMessage.setText("Unsuccesful progress!");
        }
    }
    @FXML//yeni bir sehife acilirsa bunu vermek lazimdir(FRAME)
    public void showUsers(ActionEvent actionEvent){
        utility.showStage("Users","/fxml/showUsers.fxml");
    }


}
