package de.thm.arsnova.service.comment.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.thm.arsnova.service.comment.model.Comment;
import de.thm.arsnova.service.comment.model.WebSocketPayload;

import java.util.Date;
import java.util.Objects;

public class CommentCreatedPayload implements WebSocketPayload {
    private String id;
    private String body;
    private Date timestamp;

    public CommentCreatedPayload() {}

    public CommentCreatedPayload(Comment c) {
        if (c != null) {
            id = c.getId();
            body = c.getBody();
        }
    }

    @JsonProperty("roomId")
    public String getId() {
        return id;
    }

    @JsonProperty("roomId")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CommentCreatedPayload{" +
                ", body='" + body + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentCreatedPayload that = (CommentCreatedPayload) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(body, that.body) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, body, timestamp);
    }
}
