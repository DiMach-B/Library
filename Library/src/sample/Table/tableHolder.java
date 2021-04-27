package sample.Table;

public class tableHolder {

    private String id_r;
    private String FIO;
    private String ticket;
    private String telephone;

    public String getId_r() {
        return id_r;
    }

    public void setId_r(String id_r) {
        this.id_r = id_r;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public tableHolder(String id_r, String FIO, String ticket, String telephone) {
        this.id_r = id_r;
        this.FIO = FIO;
        this.ticket = ticket;
        this.telephone = telephone;
    }



}
