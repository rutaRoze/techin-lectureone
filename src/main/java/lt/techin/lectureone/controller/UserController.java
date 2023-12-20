package lt.techin.lectureone.controller;

import lombok.RequiredArgsConstructor;
import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.model.request.User;
import lt.techin.lectureone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OpenLibraryClient openLibraryClient;



    @GetMapping
    public Object getUser() {
        return "peace";
    }

    @GetMapping("/different")
    public Object getDifferentThing() {
        return "something";
    }

    @PostMapping
    public Object returnWhatEver(
            @RequestParam String input
    ) {
        return input;

    }

    @PostMapping("/body")
    public User tryPassBody(
            @RequestBody User body
    ) {
        return userService.capitalizeName(body);
    }


    @GetMapping("/bookName")
    public String getBookByName(
            @RequestParam String bookName
    ) throws IOException, InterruptedException {
        return openLibraryClient.lookupBookByName(bookName);
    }


}
