package az.com.course.dtoModel;

public class DictionaryDTO {//Data Transfer Object
    private Integer id;
    private String dicKey;
    private String dicVal;
    private String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    public String getDicVal() {
        return dicVal;
    }

    public void setDicVal(String dicVal) {
        this.dicVal = dicVal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return dicVal;
    }
}
