package az.com.course.service;

import az.com.course.dtoModel.DictionaryDTO;
import az.com.course.dtoModel.PersonDTO;
import az.com.course.dao.DaoInterface;
import az.com.course.dao.DaoImplement;

import java.sql.SQLException;
import java.util.List;

public class ServiceImplement implements ServiceInterface {

    private DaoInterface daoInterface = new DaoImplement();


    @Override
    public void addPerson(PersonDTO personDTO) throws SQLException {
        daoInterface.addPerson(personDTO);
    }

    @Override
    public List<DictionaryDTO> comboDatasByKey(String key) throws SQLException {
       return daoInterface.comboDatasByKey(key);
    }

    @Override
    public List<PersonDTO> personDTOS() throws SQLException {
        return daoInterface.personDTOS();
    }

    @Override
    public void uptadeUser(PersonDTO personDTO) throws SQLException {
        daoInterface.uptadeUser(personDTO);
    }

    @Override
    public List<PersonDTO> findUsersByPin(String textByPin) throws SQLException {
        return daoInterface.findUserByPin(textByPin);
    }
}
