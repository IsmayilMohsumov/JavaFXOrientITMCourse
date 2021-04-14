package az.com.course.utility;

public enum Query {
    //elave edende
    PERSON_ADD("insert into users(id,name,surname,pin,email,password,person_type) values(?,?,?,?,?,?,?)"),
    //sequnce miz herdefe yeni adam elave edende id ni ozu artirir
    USER_ID("select user_seq.nextval from dual"),
    //table nin icine deyer ceken
    USER_LIST_TO_TABLE("select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL from  users u  join dictionary d on U.PERSON_TYPE=D.ID order by id"),
    //comboBoxun icine deyerleri getiren
    COMBO_DATAS("select* from dictionary where dic_key=?"),
    //update ucun
    UPDATE_USER("update users set name =? , surname = ? , pin=?,email=?,person_type=? where id=?"),
    //KeyReleased ucun LIKE vermek lazimdir ki gedib axtarsin like in icini ise dao dadolduracagiq
    USER_BY_PIN_TABLE("select U.ID, U.NAME,U.SURNAME,U.EMAIL,U.PIN,D.DIC_VAL from  users u  join dictionary d on U.PERSON_TYPE=D.ID ");
    //
//    USER1_BY_PIN_LIST("select * from users where pin like'?%'");

    private String query ;
    Query(String query) {
        this.query=query;
    }

    public String getQuery() {
        return query;
    }
}
