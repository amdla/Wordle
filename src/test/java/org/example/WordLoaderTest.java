package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class WordLoaderTest {
    private WordLoader wordLoader;

    @BeforeEach
    void setUp() {
        wordLoader = new WordLoader();
    }

    @Test
    void testLoadWords() {
        List<String> words = wordLoader.loadWords();
        assertFalse(words.isEmpty());
    }
}
