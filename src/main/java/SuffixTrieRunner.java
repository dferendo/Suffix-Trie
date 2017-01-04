import edu.um.NotOptimizedSuffixTrie.SuffixTrieImpl;
import edu.um.SuffixTrie.SuffixTrie;

import java.io.*;

/**
 * Created by dylan on 02/12/2016.
 */
public class SuffixTrieRunner {
    public static void main(String[] args) {
        try {
            // TODO no arguments passed
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
            SuffixTrieRunner suffixTreeRunner = new SuffixTrieRunner();

            String wordForSuffixTrie = bufferedReader.readLine();
            if (wordForSuffixTrie == null) {
                System.out.println("A word was not found to build a suffix trie");
            } else {
                wordForSuffixTrie = wordForSuffixTrie.replaceAll(" ", "");
                if (!wordForSuffixTrie.isEmpty()) {
                    // Third argument is optional, this is done to avoid ArrayOutOfBounds exception
                    if (args.length > 2) {
                        suffixTreeRunner.suffixTreeRunner(wordForSuffixTrie, args[1], args[2]);
                    } else {
                        suffixTreeRunner.suffixTreeRunner(wordForSuffixTrie, args[1], "");
                    }
                } else {
                    System.out.println("A word was not found to build a suffix trie");
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file " + args[0]);
        }
    }

    private void suffixTreeRunner(String word, String functionCall, String wordForLongestSubstring) {
        SuffixTrie suffixTrie = new SuffixTrieImpl(word);

        switch (functionCall) {
            case "longestRepeat" :
                System.out.println(suffixTrie.longestRepeat());
                break;
            case "longestSubstring":
                System.out.println(suffixTrie.longestSubstring(wordForLongestSubstring));
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

