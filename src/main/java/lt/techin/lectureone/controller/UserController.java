package lt.techin.lectureone.controller;

import lombok.RequiredArgsConstructor;
import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.model.mapper.BookMapper;
import lt.techin.lectureone.model.request.User;
import lt.techin.lectureone.model.response.BookResponse;
import lt.techin.lectureone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OpenLibraryClient openLibraryClient;

    @PostMapping("/body")
    public User tryPassBody(
            @RequestBody User body
    ) {
        return userService.capitalizeName(body);
    }

    @GetMapping("/bookName")
    public BookResponse getBookByName(
            @RequestParam String bookName
    ) throws IOException, InterruptedException {
        return BookMapper.map(openLibraryClient.lookupBookByName(bookName));
    }
}