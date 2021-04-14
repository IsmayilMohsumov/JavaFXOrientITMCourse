package az.com.course.dao;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;
import az.com.course.dbConfig.DbConfig;
import az.com.course.utility.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImplement implements DaoInterface {

    @Override//database e elave etmek
    public void addPerson(PersonDTO personDTO) throws SQLException {
        Connection connection = DbConfig.open();
        int id=-1;
        PreparedStatement pstSeq= connection.prepareStatement(Query.USER_ID.getQuery());
        ResultSet rs = pstSeq.executeQuery();
        if(rs.next()){
            id=rs.getInt(1);
        }


        if(id != -1){

        PreparedStatement pst= connection.prepareStatement(Query.PERSON_ADD.getQuery());//Query qu = Query.PERSON_ADD;
        pst.setInt(1, id);
        pst.setString(2, personDTO.getName());
        pst.setString(3, personDTO.getSurname());
        pst.setString(4, personDTO.getPin());
        pst.setString(5, personDTO.getEmail());
        pst.setString(6, personDTO.getPassword());
   DictionaryDTO dictionaryDTO = personDTO.getUserPersonType();
        pst.setInt(7, dictionaryDTO!=null ? dictionaryDTO.getId() : null);
        pst.execute();
        pst.close();
        }

    }

    @Override//databaseden comboBoxa deyer cekmek
    public List<DictionaryDTO> comboDatasByKey(String key) throws SQLException {
        List<DictionaryDTO> datas = new ArrayList<>();
        Connection connection = DbConfig.open();
        PreparedStatement pst = connection.prepareStatement(Query.COMBO_DATAS.getQuery());
        pst.setString(1,key);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            dictionaryDTO.setId(rs.getInt(1));
            dictionaryDTO.setDicKey(rs.getString(2));
            dictionaryDTO.setDicVal(rs.getString(3));
            dictionaryDTO.setDescription(rs.getString(4));

            datas.add(dictionaryDTO);

        }
        return datas;


    }

    @Override//databaseden tableView a deyerleri cekmek
    public List<PersonDTO> personDTOS() throws SQLException {
        List<PersonDTO> TableViewDatas = new ArrayList<>();
        Connection connection = DbConfig.open();
        PreparedStatement pst = connection.prepareStatement(Query.USER_LIST_TO_TABLE.getQuery());

        return datas(pst);
    }

    @Override//update elemek ucun
    public void uptadeUser(PersonDTO personDTO) throws SQLException {
        //set name =? , surname = ? , pin=?,email=?,person_type=? where id=?
            Connection connection=DbConfig.open();
            PreparedStatement pst = connection.prepareStatement(Query.UPDATE_USER.getQuery());
            pst.setString(1,personDTO.getName());
            pst.setString(2,personDTO.getSurname());
            pst.setString(3,personDTO.getPin());
            pst.setString(4,personDTO.getEmail());
            DictionaryDTO dictionaryType = personDTO.getUserPersonType();
            pst.setInt(5,dictionaryType != null ? dictionaryType.getId()  : null);
            pst.setInt(6,personDTO.getId());
            pst.execute();
//            pst.close();



    }

    @Override//keyReleased ucun deyerleri getirmek ucun
    public List<PersonDTO> findUserByPin(String textByPin) throws SQLException {
        Connection connection = DbConfig.open();
        String sql= Query.USER_BY_PIN_TABLE.getQuery();
        if(textByPin!=null&& !textByPin.isEmpty()){
            sql = sql + "where u.pin like '%"+textByPin+"%'";
        }

        PreparedStatement pst = connection.prepareStatement(sql);
        //deyeri methoda gonderdik
        return datas(pst);
    }


    //kod tekrarina yol verdiyimiz ucun bunu da methoda atdiq ve cagirdigda heryerde PreparedStatement deyerinde birshey gelmelidi
    private List<PersonDTO> datas (PreparedStatement pst) throws SQLException {
        List<PersonDTO> datas = new ArrayList<>();
        ResultSet resultSet= pst.executeQuery();
        while (resultSet.next()){
            PersonDTO personDTO = new PersonDTO();
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String pin = resultSet.getString(5);
            String dicVal = resultSet.getString(6);

            //Description hissesini dictionary den gotururuk ve===
            DictionaryDTO dictionaryDTO = new DictionaryDTO();
            dictionaryDTO.setDicVal(dicVal);

            personDTO.setId(id);
            personDTO.setName(name);
            personDTO.setSurname(surname);
            personDTO.setEmail(email);
            personDTO.setPin(pin);

            //===bele qaydada set edirik;
            personDTO.setUserPersonType(dictionaryDTO);

            datas.add(personDTO);
        }


        return datas;
    }



}
