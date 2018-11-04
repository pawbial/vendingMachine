package pl.sdacademy.vending.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void shouldReturnUnmodifiedTextIfLengthMatched() {
        // given
        String textToAdjust = "Ala ma kota";
        Integer expectedLength = 11;
        // when
        String adjustedText =
                StringUtil.adjustText(textToAdjust, expectedLength);
        // then
        assertEquals("Ala ma kota", adjustedText);
    }

    // "abcd" -> 8 -> "  abcd  "
    // "abc" -> 8 ->  "   abc  "
    @Test
    public void shouldTrimTooLongText() {
        // given
        String textToAdjust = "Ala ma kota";
        Integer expectedLength = 6;
        // when
        String adjustedText
                = StringUtil.adjustText(textToAdjust, expectedLength);
        // then
        assertEquals("Ala ma", adjustedText);
    }
}