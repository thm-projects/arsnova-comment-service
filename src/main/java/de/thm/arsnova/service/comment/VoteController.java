package de.thm.arsnova.service.comment;

import de.thm.arsnova.service.comment.model.Vote;
import de.thm.arsnova.service.comment.model.command.Downvote;
import de.thm.arsnova.service.comment.model.command.Upvote;
import de.thm.arsnova.service.comment.model.command.VotePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController("VoteController")
@RequestMapping("/vote")
public class VoteController extends AbstractEntityController {
    protected static final String REQUEST_MAPPING = "/vote";

    private final VoteCommandHandler commandHandler;
    private final VoteService service;
    private final VoteFindQueryService findQueryService;

    @Autowired
    public VoteController(
            final VoteCommandHandler commandHandler,
            final VoteService service,
            final VoteFindQueryService findQueryService
    ) {
        this.commandHandler = commandHandler;
        this.service = service;
        this.findQueryService = findQueryService;
    }

    @GetMapping(GET_MAPPING)
    public Vote get(@PathVariable String id) {
        return service.get(id);
    }

    @PostMapping(POST_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    public Vote post(
            @RequestBody final Vote vote,
            final HttpServletResponse httpServletResponse
    ) {
        Vote v = null;
        final VotePayload payload = new VotePayload(vote.getUserId(), vote.getCommentId());
        switch (vote.getVote()) {
            case 1:
                final Upvote upvote = new Upvote(payload);
                v = commandHandler.handle(upvote);
                break;
            case -1:
                final Downvote downvote = new Downvote(payload);
                v = commandHandler.handle(downvote);
                break;
            default:
        }

        if (v != null) {
            final String uri = UriComponentsBuilder.fromPath(REQUEST_MAPPING).path(GET_MAPPING)
                    .buildAndExpand(v.getId()).toUriString();
            httpServletResponse.setHeader(HttpHeaders.LOCATION, uri);
            httpServletResponse.setHeader(ENTITY_ID_HEADER, v.getId());

            return v;
        } else {
            throw new HttpMessageNotReadableException("Invalid request");
        }
    }

    @PostMapping(FIND_MAPPING)
    public List<Vote> find(@RequestBody final FindQuery<Vote> findQuery) {
        Set<String> ids = findQueryService.resolveQuery(findQuery);

        return service.get(new ArrayList<>(ids));
    }
}
