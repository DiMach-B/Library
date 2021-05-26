package sample.Table;

public class tableReader {

    private String id_r;
    private String name;
    private String surname;
    private String patronymic;
    private String telephone;
    private String email;
    private String num_ticket;
    private String date_birthday;

    public String getId_r() {
        return id_r;
    }

    public void setId_r(String id_r) {
        this.id_r = id_r;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(String num_ticket) {
        this.num_ticket = num_ticket;
    }

    public String getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(String date_birthday) {
        this.date_birthday = date_birthday;
    }

    public tableReader(String id_r, String name, String surname, String patronymic, String telephone
            , String email, String num_ticket, String date_birthday) {
        this.id_r = id_r;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.telephone = telephone;
        this.email = email;
        this.num_ticket = num_ticket;
        this.date_birthday = date_birthday;
    }
}
