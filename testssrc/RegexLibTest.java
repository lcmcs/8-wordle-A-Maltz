import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class RegexLibTest {

    @Test
    void properNameTest() {
        String test = "Reuven";
        String failTest = "pizza";
        assertTrue(RegexLib.properName(test));
        assertFalse(RegexLib.properName(failTest));
    }

    @Test
    void palindromeTest() {
        String test = "abcdeedcba";
        String failTest = "pizzapizza";
        String capsTest = "ABCdEeDCbA";
        assertTrue(RegexLib.palindrome(test));
        assertFalse(RegexLib.palindrome(failTest));
        assertTrue(RegexLib.palindrome(capsTest));
    }

    @Test
    void ancestorTest() {
        String test = "Great-grandmother";
        String failTest = "Great-mother";
        assertTrue(RegexLib.ancestor(test));
        assertFalse(RegexLib.ancestor(failTest));
    }

    @Test
    void integerTest() {
        String test1 = "123";
        String failTest = "pizzapizza";
        String failTest2 = "0123";
        String test2 = "1230";
        String test4 = "123.4";
        String failTest4 = "123.";
        String test5 = "123.0";
        String test6 = "123.045";
        assertTrue(RegexLib.integer(test1));
        assertFalse(RegexLib.integer(failTest));
        assertTrue(RegexLib.integer(test2));
        assertFalse(RegexLib.integer(failTest2));
        assertTrue(RegexLib.integer(test4));
        assertFalse(RegexLib.integer(failTest4));
        assertTrue(RegexLib.integer(test5));
        assertTrue(RegexLib.integer(test6));
    }

    @Test
    void wordleMatchesTest() {
        List<WordleResponse> guess1 = Arrays.asList(
                new WordleResponse('T', 0, LetterResponse.WRONG_LETTER),
                new WordleResponse('R', 1, LetterResponse.WRONG_LETTER),
                new WordleResponse('A', 2, LetterResponse.WRONG_LETTER),
                new WordleResponse('I', 3, LetterResponse.WRONG_LETTER),
                new WordleResponse('N', 4, LetterResponse.WRONG_LOCATION)
        );

        List<WordleResponse> guess2 = Arrays.asList(
                new WordleResponse('C', 0, LetterResponse.WRONG_LETTER),
                new WordleResponse('O', 1, LetterResponse.WRONG_LETTER),
                new WordleResponse('U', 2, LetterResponse.WRONG_LETTER),
                new WordleResponse('G', 3, LetterResponse.WRONG_LETTER),
                new WordleResponse('H', 4, LetterResponse.CORRECT_LOCATION)
        );
        List<List<WordleResponse>> guesses = new ArrayList<>();
        guesses.add(guess1);
        guesses.add(guess2);
        assertEquals("[nymph]", RegexLib.wordleMatches(guesses).toString());
    }
}