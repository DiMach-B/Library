package sample.Table;

public class tableExtraditionReturn {

    private String id_r;
    private String surname;
    private String name;
    private String patronymic;
    private String numberTicket;
    private String id_b;
    private String nameBook;
    private String FIO;

    public tableExtraditionReturn(String id_r, String surname, String name, String patronymic, String numberTicket, String id_b, String nameBook, String FIO) {
        this.id_r = id_r;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.numberTicket = numberTicket;
        this.id_b = id_b;
        this.nameBook = nameBook;
        this.FIO = FIO;
    }

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
    }

    public String getId_r() {
        return id_r;
    }

    public void setId_r(String id_r) {
        this.id_r = id_r;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(String numberTicket) {
        this.numberTicket = numberTicket;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
