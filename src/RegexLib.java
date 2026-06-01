import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class RegexLib {
    public static boolean properName(String name) {
        if (name == null) return false;
        return name.matches("^[A-Z][a-z]+$");
    }

    public static boolean palindrome(String s) {
        if (s == null) return false;
        return s.matches("^(?i)([a-z])([a-z])([a-z])([a-z])([a-z])\\5\\4\\3\\2\\1$");
    }

    public static boolean integer(String s) {
        if (s == null) return false;
        return s.matches("^[+-]?(?:0|[1-9]\\d*)(?:\\.\\d+)?$");
    }

    public static boolean ancestor(String s) {
        if (s == null) return false;
        return s.matches("^(?i)((great-)*grand)?(mother|father)$");
    }

    public static List<String> wordleMatches(List<List<WordleResponse>> words) {
        List<String> dictionary = getDictionary();
        String[] knownChars = new String[5];
        StringBuilder[] yellowChars = new StringBuilder[5];
        for (int i = 0; i < 5; i++) {
            yellowChars[i] = new StringBuilder();
        }
        Set<Character> inTheWord = new HashSet<>(5);
        Set<Character> notInTheWord = new HashSet<>(24);

        for (List<WordleResponse> guess : words) {
            for (WordleResponse wordleResponse : guess) {
                char c = Character.toLowerCase(wordleResponse.c);
                if (wordleResponse.resp == LetterResponse.CORRECT_LOCATION) {
                    knownChars[wordleResponse.index] = String.valueOf(c);
                    inTheWord.add(c);
                }
                else if (wordleResponse.resp == LetterResponse.WRONG_LOCATION) {
                    inTheWord.add(c);
                    yellowChars[wordleResponse.index].append(c);
                }
                else if (wordleResponse.resp == LetterResponse.WRONG_LETTER)
                    notInTheWord.add(c);
            }
        }
        notInTheWord.removeAll(inTheWord);

        StringBuilder regex = new StringBuilder("^");
        for (char c : inTheWord)
            regex.append("(?=.*").append(c).append(")");
        for (char c : notInTheWord)
            regex.append("(?!.*").append(c).append(")");
        for (int i = 0; i < 5; i++) {
            if (knownChars[i] != null)
                regex.append(knownChars[i]);
            else if (!yellowChars[i].isEmpty())
                regex.append("[^").append(yellowChars[i]).append("]");
            else {
                regex.append("[a-z]");
            }
        }
        regex.append("$");

        Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
        List<String> results = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == 5 && pattern.matcher(word).matches())
                results.add(word);
        }

        return results;
    }

    private static List<String> getDictionary() {
        List<String> dictionary = new ArrayList<>();
        try {
            File f = new File("dictionary.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String word = s.nextLine().trim();
                if (word.length() == 5) {
                    dictionary.add(word);
                }
            }
            s.close();
        } catch (Exception e) {
            System.out.println("Couldn't find the dictionary.txt file.");
        }
        return dictionary;
    }
}
