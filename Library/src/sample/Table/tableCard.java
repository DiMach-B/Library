package sample.Table;

public class tableCard {
    private String id_i;
    private String name;
    private String FIO;

    public String getId_i() {
        return id_i;
    }

    public void setId_i(String id) {
        this.id_i = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getDate_get() {
        return date_get;
    }

    public void setDate_get(String date_get) {
        this.date_get = date_get;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    private String date_get;

    public tableCard(String id_i, String name, String FIO, String date_get, String date_return) {
        this.id_i = id_i;
        this.name = name;
        this.FIO = FIO;
        this.date_get = date_get;
        this.date_return = date_return;
    }

    private String date_return;
}
