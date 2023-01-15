package com.example.relation;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (wrongPattern(words)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields should only contains letter from A-Z (a-z) and whitespace");
        }

        return wordsRepository.save(lowerCasingValues(words));
    }

    private boolean wrongPattern(Words words) {
        Pattern pattern = Pattern.compile("[^a-zA-Z\\s]");
        return (pattern.matcher(words.getWord1()).find()
                || pattern.matcher(words.getWord2()).find()
                || pattern.matcher(words.getRelation()).find());
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
