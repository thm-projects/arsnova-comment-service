package de.thm.arsnova.service.comment.service.persistence;

import de.thm.arsnova.service.comment.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, String> {
    List<Vote> findByCommentId(String commentId);
    Vote findByCommentIdAndUserId(String commentId, String userId);
}