package com.example.exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;
import java.util.Map;

@RestController
public class DictionaryController {
    private final Map<String, String> dictionaryMap = Map.ofEntries(
            Map.entry("hello", "xin chào"),
            Map.entry("apple", "quả táo"),
            Map.entry("banana", "quả chuối"),
            Map.entry("orange", "quả cam"),
            Map.entry("lemon", "quả chanh"),
            Map.entry("melon", "quả dưa"),
            Map.entry("watermelon", "quả dưa hấu"),
            Map.entry("blueberry", "quả việt quất")
    );

    @GetMapping("/dictionary")
    public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String word) {
        String translation = dictionaryMap.get(word.trim().toLowerCase());

        if (translation != null) {
            return ResponseEntity.ok(translation);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy trong từ đển");
    }
}

