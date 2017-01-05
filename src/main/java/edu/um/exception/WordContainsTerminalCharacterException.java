package edu.um.exception;


/**
 * Strings that contain the terminal character will not be accepted since it will mess with
 * the functions of the Suffix Trie.
 */
public class WordContainsTerminalCharacterException extends Exception {

    public WordContainsTerminalCharacterException() {
        super("Word contains the terminal character ~, try again using a different symbol");
    }
}
