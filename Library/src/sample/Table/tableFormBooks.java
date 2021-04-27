package sample.Table;

public class tableFormBooks {

    private String id_i;
    private String name;
    private String FIO;
    private String cipher;
    private String topic;
    private String publish;
    private String date_p;
    private String quality;

    public String getId_i() {
        return id_i;
    }

    public void setId_i(String id_i) {
        this.id_i = id_i;
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

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getDate_p() {
        return date_p;
    }

    public void setDate_p(String date_p) {
        this.date_p = date_p;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public tableFormBooks(String id_i, String name, String FIO, String cipher, String publish, String topic, String date_p, String quality, String get) {
        this.id_i = id_i;
        this.name = name;
        this.FIO = FIO;
        this.cipher = cipher;
        this.topic = topic;
        this.publish = publish;
        this.date_p = date_p;
        this.quality = quality;
        this.get = get;
    }

    private String get;

}
