package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WordLoader {
    private static final Logger LOGGER = Logger.getLogger(WordLoader.class.getName());

    public List<String> loadWords() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load words from file", e);
            return new ArrayList<>();
        }
    }
}