package appmoviles.com.chatandroid.model;

public class FCMMessage {

    private Message data;
    private String to;

    public FCMMessage(String to, Message data) {
        this.to = to;
        this.data = data;
    }

    public FCMMessage() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Message getData() {
        return data;
    }

    public void setData(Message data) {
        this.data = data;
    }
}
