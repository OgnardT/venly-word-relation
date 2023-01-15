package com.example.relation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
public class MainController {

    private final WordsRepository wordsRepository;

    public MainController(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    @PostMapping("/words")
    Words addWord(@RequestBody Words words) {
        if (Objects.equals(words.getWord1(), "") || Objects.equals(words.getWord2(), "") ||Objects.equals(words.getRelation(), "")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields should not be empty");
        }
        return wordsRepository.save(words);
    }

    @GetMapping("/words")
    List<Words> getWords() {
        return wordsRepository.findAll();
    }

    @GetMapping("/words/{relation}")
    List<Words> getWords(@PathVariable String relation) {
        return wordsRepository.findAll().stream().filter(x -> Objects.equals(x.getRelation(), relation)).toList();
    }
}
