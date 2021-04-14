package az.com.course.dao;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface {
    void addPerson(PersonDTO personDTO) throws SQLException;

    List<DictionaryDTO> comboDatasByKey(String key)throws SQLException ;

    List<PersonDTO> personDTOS() throws SQLException;

    void uptadeUser(PersonDTO personDTO) throws SQLException;

    List<PersonDTO> findUserByPin(String textByPin) throws SQLException;
}
