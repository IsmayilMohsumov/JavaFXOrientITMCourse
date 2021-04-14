package az.com.course.dtoModel;

public class PersonDTO {
    private Integer id;
    private String name;
    private String surname;
    private String pin;
    private String email;
    private String password;
    private DictionaryDTO userPersonType;


/////////////////////// --- POSITIONU CIXARDMAQ UCUN RETURN HISSESINDE ONSUZDA GETDICVALI QAYTARIR
    private String position;

    public String getPosition() {
        return userPersonType.getDicVal();
    }

    public void setPosition(String position) {
        this.position = userPersonType.getDicVal();
    }
    /////////////////////////------------------/////////////////


    public DictionaryDTO getUserPersonType() {
        return userPersonType;
    }

    public void setUserPersonType(DictionaryDTO userPersonType) {
        this.userPersonType = userPersonType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  /*  public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }*/

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pin='" + pin + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userPersonType=" + userPersonType +
                ", position='" + position + '\'' +
                '}';
    }
}
