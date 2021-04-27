package sample.Table;

public class tableResults {

    private String id_d;
    private String name;
    private String FIO;
    private String condition;
    private String date_get;
    private String date_return;

    public String getId_d() {
        return id_d;
    }

    public void setId_d(String id_d) {
        this.id_d = id_d;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public tableResults(String id_d, String name, String FIO, String condition, String date_get, String date_return) {
        this.id_d = id_d;
        this.name = name;
        this.FIO = FIO;
        this.condition = condition;
        this.date_get = date_get;
        this.date_return = date_return;
    }



}
