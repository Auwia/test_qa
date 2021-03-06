package ch.app.test_qa.core.restapi;

public class Post {
    private int userId, id;
    private String title, body;

    public Post(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isValidTitle(String title) {
        return title != null;
    }

    public boolean isValidBody(String body) {
        return body != null;
    }

}
