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
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class uptadeUserController implements Initializable {
    private ServiceInterface serviceInterface = new ServiceImplement();//service in icine girmek ucun obyektini yaradiriq
    private Utility utility = new Utility();




    private Integer id;

    public Label labelErrorId;
    @FXML
    private TextField userName;
    @FXML
    private TextField userSurname;
    @FXML
    private TextField userPin;
    @FXML
    private TextField userEmail;

//    @FXML
//    private Button buttonDaxilEt;

    @FXML
    private ComboBox<DictionaryDTO> userPersonType;


    public List<DictionaryDTO> comboDatas (String key) throws SQLException {//key yuxarda ObservableListden gelir
        return serviceInterface.comboDatasByKey(key);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Fieldslari doldurduq
    public void setFields(PersonDTO fields){
        id = fields.getId();
        userName.setText(fields.getName());
        userSurname.setText(fields.getSurname());
        userPin.setText(fields.getPin());
        userEmail.setText(fields.getEmail());

        try {//combobox u doldurmaq ucun
            userPersonType.setItems(FXCollections.observableArrayList(comboDatas("personType")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        userPersonType.getSelectionModel().select(fields.getUserPersonType());

    }


    //deyerleri gotururuk eger deyishilmish deyer varsa
    //uptade etmek ucun deyerleri service daha sonra dao ya oradanda database e
    public void uptadeUser(ActionEvent actionEvent){

        String name = userName.getText();
        String surname = userSurname.getText();
        String pin = userPin.getText();
        String email = userEmail.getText();

        DictionaryDTO userPersonType = this.userPersonType.getSelectionModel().getSelectedItem();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);
        personDTO.setName(name);
        personDTO.setSurname(surname);
        personDTO.setPin(pin);
        personDTO.setEmail(email);
        personDTO.setUserPersonType(userPersonType);
        try{
            serviceInterface.uptadeUser(personDTO);
            //burada eger duzgun isleyerse ashagidaki kodda Table ni yeniden acir -
            utility.showStage("Users","/fxml/showUsers.fxml");
            //- ve burada ise kohne ni yeni uptade sehifesni baglayir
            Utility.hideWindow(actionEvent);
        }catch (Exception e){
            //eger xeta bash vererse yaratdigim labelda xeta cixacaq
            labelErrorId.setText("Something went wrong!");

            e.printStackTrace();
        }

    }
}
