package db2.redsocial.models;

public class Message {
    public Integer status;
    public String message;
    
    public Message(Integer s, String m) {
        status = s;
        message = m; 
    }
}
