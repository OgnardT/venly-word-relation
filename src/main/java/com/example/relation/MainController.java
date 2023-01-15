package com.example.relation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class MainController {

    private final WordsRepository wordsRepository;

    public MainController(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    @PostMapping("/add")
    Words newWords(@RequestBody Words words) {
        if (Objects.equals(words.getWord1(), "") || Objects.equals(words.getWord2(), "") ||Objects.equals(words.getRelation(), "")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields should not be empty");
        }
        return wordsRepository.save(words);
    }

}
