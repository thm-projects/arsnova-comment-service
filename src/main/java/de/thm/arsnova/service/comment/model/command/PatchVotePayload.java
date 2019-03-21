package de.thm.arsnova.service.comment.model.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.thm.arsnova.service.comment.model.WebSocketPayload;

import java.util.Map;

public class PatchVotePayload implements WebSocketPayload {
    private String id;
    private Map<String, Object> changes;

    public PatchVotePayload() {
    }

    public PatchVotePayload(
            final String id,
            final Map<String, Object> changes
    ) {
        this.id = id;
        this.changes = changes;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("changes")
    public Map<String, Object> getChanges() {
        return changes;
    }

    @JsonProperty("changes")
    public void setChanges(Map<String, Object> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return "PatchVotePayload{" +
                "id='" + id + '\'' +
                ", changes=" + changes +
                '}';
    }
}
