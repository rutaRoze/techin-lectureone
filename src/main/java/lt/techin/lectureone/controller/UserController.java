package lt.techin.lectureone.controller;

import lombok.RequiredArgsConstructor;
import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.model.mapper.BookMapper;
import lt.techin.lectureone.model.response.BookResponse;
import lt.techin.lectureone.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController()
//@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OpenLibraryClient openLibraryClient;

    @GetMapping("/bookName")
    public BookResponse getBookByName(
            @RequestParam String bookName
    ) throws IOException, InterruptedException {
        return BookMapper.map(openLibraryClient.lookupBookByName(bookName));
    }


}