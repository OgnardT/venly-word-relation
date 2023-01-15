package com.example.relation;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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
        return wordsRepository.save(lowerCasingValues(words));
    }

    private Words lowerCasingValues(Words words) {
        words.setRelation(words.getRelation().toLowerCase().strip());
        words.setWord1(words.getWord1().toLowerCase().strip());
        words.setWord2(words.getWord2().toLowerCase().strip());
        return words;
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
