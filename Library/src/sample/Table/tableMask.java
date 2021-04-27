package sample.Table;

public class tableMask {

    private String name;
    private String topic;
    private String publish;
    private String authorName;
    private String authorSurname;
    private String authorPatronymic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public void setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
    }

    public tableMask(String name, String topic, String publish, String authorName,
                     String authorPatronymic, String authorSurname) {
        this.name = name;
        this.topic = topic;
        this.publish = publish;
        this.authorName = authorName;
        this.authorPatronymic = authorPatronymic;
        this.authorSurname = authorSurname;

    }





}
