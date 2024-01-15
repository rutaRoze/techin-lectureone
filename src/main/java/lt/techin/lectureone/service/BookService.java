package lt.techin.lectureone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.lectureone.exeption.AuthorNotFoundException;
import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.model.mapper.BookMapper;
import lt.techin.lectureone.model.request.ReactionAction;
import lt.techin.lectureone.model.request.RecordReactionRequest;
import lt.techin.lectureone.model.response.BookResponse;
import lt.techin.lectureone.model.response.ReactionByAuthorResponse;
import lt.techin.lectureone.model.response.UserReactionResponse;
import lt.techin.lectureone.persistence.AuthorRepository;
import lt.techin.lectureone.persistence.ReactionRepository;
import lt.techin.lectureone.persistence.modal.AuthorRecord;
import lt.techin.lectureone.persistence.modal.ReactionRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static lt.techin.lectureone.model.request.ReactionAction.DISLIKE;
import static lt.techin.lectureone.model.request.ReactionAction.LIKE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final OpenLibraryClient openLibraryClient;
    private final AuthorRepository authorRepository;
    private final ReactionRepository reactionRepository;

    public BookResponse getAuthorWorks(String author, int count) throws IOException, InterruptedException {

        String olid = "";

        Optional<AuthorRecord> optionalAuthorRecord = authorRepository.findById(sanitizeAuthorKey(author));

        if (optionalAuthorRecord.isPresent()) {
            olid = optionalAuthorRecord.get().getOlid();
            log.info("Got olid from {} from DB", author);
        }

        if (olid.isBlank()) {
            olid = openLibraryClient.getAuthorOlid(author);
            log.info("Got olid from {} from OpenLibrary", author);
        }

        if (olid.isBlank()) {
            throw new AuthorNotFoundException("Could not find author: " + author);
        }

        authorRepository.save(new AuthorRecord(sanitizeAuthorKey(author), olid));

        AuthorWorksResponse authorWorksResponse = openLibraryClient.getWorks(olid);

        return BookMapper.map(authorWorksResponse, author);
    }

    public void recordReaction(RecordReactionRequest recordReactionRequest) {

        ReactionRecord reactionRecord = new ReactionRecord(
                recordReactionRequest.getUuid(),
                recordReactionRequest.getOlid(),
                recordReactionRequest.getAction()
        );

        reactionRepository.save(reactionRecord);
    }

    public UserReactionResponse getUserReaction(String uuid, ReactionAction action) {
        List<ReactionRecord> reactionRecordList = reactionRepository.findByUuid(uuid);

        log.debug("got records from db from user {} : {}", uuid, reactionRecordList);

        UserReactionResponse userReactionResponse = new UserReactionResponse();


        try {
            switch (action) {
                case LIKE -> userReactionResponse.setLike(getOlidByAction(reactionRecordList, LIKE));

                case DISLIKE -> userReactionResponse.setDislike(getOlidByAction(reactionRecordList, DISLIKE));
            }
        } catch (NullPointerException e) {
            userReactionResponse.setLike(getOlidByAction(reactionRecordList, LIKE));
            userReactionResponse.setDislike(getOlidByAction(reactionRecordList, DISLIKE));
        }

        return userReactionResponse;
    }


    public ReactionByAuthorResponse getReactionByAuthor(String olid, ReactionAction action) {
        List<ReactionRecord> reactionRecordList = reactionRepository.findByOlid(olid);

        log.debug("got records from db from author {} : {}", olid, reactionRecordList);

        ReactionByAuthorResponse reactionByAuthorResponse = new ReactionByAuthorResponse();


        try {
            switch (action) {
                case LIKE -> reactionByAuthorResponse.setLike(getUuidByAction(reactionRecordList, LIKE));

                case DISLIKE -> reactionByAuthorResponse.setDislike(getUuidByAction(reactionRecordList, DISLIKE));
            }
        } catch (NullPointerException e) {
            reactionByAuthorResponse.setLike(getUuidByAction(reactionRecordList, LIKE));
            reactionByAuthorResponse.setDislike(getUuidByAction(reactionRecordList, DISLIKE));
        }

        return reactionByAuthorResponse;
    }


    protected static String sanitizeAuthorKey(String author) {
        return author
                .toUpperCase(Locale.ROOT)
                .strip()
                .replaceAll(" ", "_");
    }

    protected List<String> getOlidByAction(List<ReactionRecord> reactionRecordList, ReactionAction action) {

        return reactionRecordList.stream()
                .filter((reactionRecord -> reactionRecord.getReactionAction().equalsIgnoreCase(action.name())))
                .map(ReactionRecord::getOlid)
                .toList();
    }

    protected List<String> getUuidByAction(List<ReactionRecord> reactionRecordList, ReactionAction action) {

        return reactionRecordList.stream()
                .filter((reactionRecord -> reactionRecord.getReactionAction().equalsIgnoreCase(action.name())))
                .map(ReactionRecord::getUuid)
                .toList();
    }

}
