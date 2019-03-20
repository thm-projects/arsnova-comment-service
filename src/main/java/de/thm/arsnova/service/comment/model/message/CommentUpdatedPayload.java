package de.thm.arsnova.service.comment.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.thm.arsnova.service.comment.model.Comment;

public class CommentUpdatedPayload implements WebSocketPayload {
    private String id;
    private String body;
    private boolean read;
    private boolean favorite;
    private boolean correct;

    public CommentUpdatedPayload() {
    }

    public CommentUpdatedPayload(Comment c) {
        this.id = c.getId();
        this.body = c.getBody();
        this.read = c.isRead();
        this.favorite = c.isFavorite();
        this.correct = c.isCorrect();
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
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

    @JsonProperty("read")
    public boolean isRead() {
        return read;
    }

    @JsonProperty("read")
    public void setRead(boolean read) {
        this.read = read;
    }

    @JsonProperty("favorite")
    public boolean isFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("correct")
    public boolean isCorrect() {
        return correct;
    }

    @JsonProperty("correct")
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
