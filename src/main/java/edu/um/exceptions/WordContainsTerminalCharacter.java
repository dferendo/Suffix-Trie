package edu.um.exceptions;

/**
 * Created by dylan on 05/01/2017.
 */
public class WordContainsTerminalCharacter extends Exception {

    public WordContainsTerminalCharacter() {
        super("Word contains the terminal character ~, try again using a different symbol");
    }
}
