package Objects;

import java.util.Date;

public class Comment {
    private int ownerID;
    private int writerID;
    private String comment;
    private int commentID;

    public Comment(int ownerID, int writerID, int commentID, String comment, Date dateOfWrite) {
        this.ownerID = ownerID;
        this.writerID = writerID;
        this.commentID = commentID;
        this.comment = comment;
        this.dateOfWrite = dateOfWrite;
    }

    public Date getDateOfWrite() {
        return dateOfWrite;
    }

    public void setDateOfWrite(Date dateOfWrite) {
        this.dateOfWrite = dateOfWrite;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getWriterID() {
        return writerID;
    }

    public void setWriterID(int writerID) {
        this.writerID = writerID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    Date dateOfWrite;
}
