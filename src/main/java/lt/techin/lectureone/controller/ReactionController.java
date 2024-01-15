package lt.techin.lectureone.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.lectureone.model.request.ReactionAction;
import lt.techin.lectureone.model.request.RecordReactionRequest;
import lt.techin.lectureone.model.response.ReactionByAuthorResponse;
import lt.techin.lectureone.model.response.UserReactionResponse;
import lt.techin.lectureone.service.BookService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/react")
@RequiredArgsConstructor
public class ReactionController {

    private final BookService bookService;

    @PostMapping
    public void recordReaction(
            @RequestBody @Valid RecordReactionRequest recordReactionRequest) {

        log.debug("received POST /react with body {}", recordReactionRequest);

        bookService.recordReaction(recordReactionRequest);
    }


    @GetMapping
    public UserReactionResponse getUserReactions(
            @RequestParam @UUID String uuid,
            @RequestParam(required = false) ReactionAction action) {

        return bookService.getUserReaction(uuid, action);
    }

    @GetMapping("/author")
    public ReactionByAuthorResponse getReactionsByAuthor(
            @RequestParam @Pattern(regexp = "OL[\\d]+A") String olid,
            @RequestParam(required = false) ReactionAction action) {

        return bookService.getReactionByAuthor(olid, action);
    }
}
