# Project Title

Simple overview of use/purpose.

Project Title: Mancala Game with GUI

Simplie Overview: Mancala game simulation.

## Description

An in-depth paragraph about your project and overview of use.

This project is a Mancala game simulation. At its core is the MancalaGame class, which serves as the engine for the game's logic and user interactions. The GamesRules class is responsible for overseeing the game board. It manages pits and stores, and takes care of tasks like stone distribution, pit status checks, and board initialization. The Pit class is in charge of handling individual pits. It allows for the addition and removal of stones and provides a string representation for these pits. The Store class is responsible for managing a player's store. It enables stone addition and removal, allows for owner retrieval, and can generate a string representation. The Player class represents the game's players, including their names and stores. It also provides methods for retrieving and setting player information, along with the capability to generate a string representation of the player. It models the game board, complete with pits and stores, ensuring strict adherence to the Mancala game rules. The program keeps tabs on the current player, facilitates stone distribution, executes the capture of an opponent's stones when necessary, and decisively determines the game's outcome. Players interact with the program by running it and taking turns to play Mancala, a classic two-player board game. It's designed with user-friendliness in mind and even provides a clear string representation of the game state.
and author together. 

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

gradle.

### Executing program

* How to build and run the program

* Make sure you are in the right folder "GP2"
* In the terminal type and enter "gradle build"
* Then type and enter "gradle echo" in the terminal
* you will be given the following, copy and paste either line in to terminal to run the code:
    * To run the program from jar: java -jar build/libs/MancalaGame.jar
    * To run the program from class files: java -cp build/libs/MancalaGame.jar:src ui.TextUI
* The expected output is:


## Limitations

What isn't done? What things cause errors?  

There are none, everything works fine.

## Author Information

Your name and contact information including your email address

    Name: Shanza Khan
    Student ID: 1231936
    Uog Email: shanza@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.

* 0.1
    * the code if generally complete, however many errors and no comments
* 0.2
    * the errors are generally resolved, they were mainly checkstyle errors
* 0.3 
    * the README files are complete

## Acknowledgments




