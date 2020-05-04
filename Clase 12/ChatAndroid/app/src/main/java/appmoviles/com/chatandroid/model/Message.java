package appmoviles.com.chatandroid.model;

public class Message {

    private String id;
    private String body;
    private String userId;
    private long timestamp;

    public Message() {
    }

    public Message(String id, String body, String userId, long timestamp) {
        this.id = id;
        this.body = body;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
