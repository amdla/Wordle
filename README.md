# Wordle Game


#### Video Demo:  <URL HERE>


#### Hey, you can download just the Wordle.exe file and try it out yourself!

#### Required java version 17+





## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Classes](#classes)
    - [Game](#game)
    - [GameView](#gameview)
    - [WordLoader](#wordloader)
    - [CellColorManager](#cellcolormanager)
- [Tests](#tests)
    - [GameTest](#gametest)
    - [WordLoaderTest](#wordloadertest)

## Introduction

This project is a Java implementation of the Wordle game. The game selects a random word from a list, and the player has to guess the word. The game provides feedback on the player's guesses, indicating which letters are correct and in the correct position.

## Project Structure

The project follows a standard Maven project structure:

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── org
│   │   │   │   ├── example
│   │   │   │   │   ├── Game.java
│   │   │   │   │   ├── GameView.java
│   │   │   │   │   ├── WordLoader.java
│   │   │   │   │   ├── CellColorManager.java
│   │   ├── resources
│   │   │   └── words.txt
│   ├── test
│   │   ├── java
│   │   │   ├── org
│   │   │   │   ├── example
│   │   │   │   │   ├── GameTest.java
│   │   │   │   │   ├── WordLoaderTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Classes

### Game

The `Game` class is the main class of the game. It manages the game state, including the current word, the player's guesses, and whether the game has been won or lost. It also interacts with the `GameView` to update the user interface.

### GameView

The `GameView` class is responsible for the user interface of the game. It displays the current state of the game to the player, and handles user input.

### WordLoader

The `WordLoader` class is responsible for loading the list of words from a file. The game selects the current word from this list.

### CellColorManager

The `CellColorManager` class is responsible for managing the colors of the cells in the game view. It determines the color of each cell based on the player's guesses and the current word.

## Tests

### GameTest

The `GameTest` class contains unit tests for the `Game` class. It tests the game logic, including the handling of guesses and the game state.

### WordLoaderTest

The `WordLoaderTest` class contains unit tests for the `WordLoader` class. It tests the loading of words from a file.

## Conclusion

This project is a simple implementation of the Wordle game in Java. It demonstrates the use of several key concepts in Java, including object-oriented programming, file I/O, and unit testing.
