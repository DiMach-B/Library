package sample.Table;

public class tableTop {

    private String id_b;
    private String quality;
    private String name;
    private String FIO;

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
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

    public tableTop(String id_b, String quality, String name, String FIO) {
        this.id_b = id_b;
        this.quality = quality;
        this.name = name;
        this.FIO = FIO;
    }



}
