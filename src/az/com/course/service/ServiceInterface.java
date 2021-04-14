package az.com.course.service;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterface {
    void addPerson(PersonDTO personDTO) throws SQLException;


    List<DictionaryDTO> comboDatasByKey(String key)throws SQLException;


    List<PersonDTO> personDTOS() throws SQLException;

    void uptadeUser(PersonDTO personDTO) throws SQLException;

    List<PersonDTO> findUsersByPin(String textByPin) throws SQLException;
}
