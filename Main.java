package com.yash.sentiment.backend;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Main {

    private static final Set<String> POSITIVE = Set.of("good","excellent","helpful","clear");
    private static final Set<String> NEGATIVE = Set.of("bad","poor","confusing","difficult");

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> input) {
        String text = input.getOrDefault("text", "").toLowerCase();
        int score = 0;
        for (String w : text.split("\\W+")) {
            if (POSITIVE.contains(w)) score++;
            if (NEGATIVE.contains(w)) score--;
        }
        String sentiment = score > 0 ? "Positive" :
                           score < 0 ? "Negative" :
                           "Neutral";

        return Map.of(
                "text", text,
                "sentiment", sentiment,
                "score", score
        );
    }
}
