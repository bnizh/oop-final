package Objects;

import java.util.Date;

public class Message {
    private int writerID;
    private int receiverID;
    private String messageContent;
    private Date dateOfSend;
    private int messageID;
    private boolean read;

    public Message(boolean read, int writerID, int receiverID, String messageContent, Date dateOfSend, int messageID) {
        this.read = read;
        this.writerID = writerID;
        this.receiverID = receiverID;
        this.messageContent = messageContent;
        this.dateOfSend = dateOfSend;
        this.messageID = messageID;

    }

    public Message(int writerID, int receiverID, String messageContent) {
        this.messageContent = messageContent;
        this.receiverID = receiverID;
        this.writerID = writerID;
    }


    public int getMessageID() {
        return messageID;
    }

    public boolean isRead() {
        return read;
    }

    public int getWriterID() {
        return writerID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getDateOfSend() {
        return dateOfSend;
    }


    public void setRead(boolean read) {
        this.read = read;
    }

    public void setWriterID(int writerID) {
        this.writerID = writerID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setDateOfSend(Date dateOfSend) {
        this.dateOfSend = dateOfSend;
    }


}
