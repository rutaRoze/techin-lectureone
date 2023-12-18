package lt.techin.lectureone.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

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
}
