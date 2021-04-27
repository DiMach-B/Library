package sample.Table;



public class tableOne {

    public String id;
    public String name;
    public String surname;
    public String nameA;
    public String patr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    public tableOne(String id, String name, String surname, String nameA, String patr) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nameA = nameA;
        this.patr = patr;
    }
}

