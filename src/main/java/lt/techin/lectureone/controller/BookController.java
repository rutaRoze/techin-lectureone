package lt.techin.lectureone.controller;


import lombok.RequiredArgsConstructor;
import lt.techin.lectureone.model.response.BookResponse;
import lt.techin.lectureone.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/works")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public BookResponse getAuthorWorks(
            @RequestParam String author
    ) throws IOException, InterruptedException {
        //log.debug("Called get author works endpoint");

        return bookService.getAuthorWorks(author);
    }
}
