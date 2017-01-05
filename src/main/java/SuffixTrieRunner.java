import edu.um.exception.WordContainsTerminalCharacterException;
import edu.um.not_optimized_suffix_trie.SuffixTrieImpl;
import edu.um.suffix_trie.SuffixTrie;

import java.io.*;

/**
 * Created by dylan on 02/12/2016.
 */
public class SuffixTrieRunner {

    public static void main(String[] args) {
        // If not enough arguments passed, they will be caught and the program stops.
        try(final FileReader reader = new FileReader(args[0])) {
            final BufferedReader bufferedReader = new BufferedReader(reader);
            final SuffixTrieRunner suffixTreeRunner = new SuffixTrieRunner();

            final String wordForSuffixTrie = bufferedReader.readLine();
            if (wordForSuffixTrie == null) {
                System.out.println("A word was not found to build a suffix trie");
            } else {
                // Third argument is optional, this is done to avoid ArrayOutOfBounds exception
                if (args.length > 2) {
                    suffixTreeRunner.suffixTreeRunner(wordForSuffixTrie, args[1], args[2]);
                } else {
                    suffixTreeRunner.suffixTreeRunner(wordForSuffixTrie, args[1], null);
                }
            }
            bufferedReader.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough arguments passed!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file " + args[0]);
        } catch (WordContainsTerminalCharacterException e) {
            System.out.println(e.getMessage());
        }
    }

    private void suffixTreeRunner(final String word, final String functionCall, final String wordForLongestSubstring)
            throws ArrayIndexOutOfBoundsException, WordContainsTerminalCharacterException {
        final SuffixTrie suffixTrie = new SuffixTrieImpl(word);

        switch (functionCall) {
            case "longestRepeat" :
                System.out.println("LongestRepeat: " + suffixTrie.longestRepeat());
                break;
            case "longestSubstring":
                if (wordForLongestSubstring == null) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                System.out.println("LongestSubstring: " + suffixTrie.longestSubstring(wordForLongestSubstring));
                break;
            case "show":
                suffixTrie.show();
                System.out.println();
                break;
            default:
                System.out.print("No function found, functions available are longestRepeat, " +
                        "longestSubstring and show.");
        }
    }
}

