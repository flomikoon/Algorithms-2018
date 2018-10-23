package lesson2;

import kotlin.Pair;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JavaAlgorithmsTest {


    @Test
    void optimizeBuyAndSell() {
        //assertEquals(new Pair<>(16, 125), (JavaAlgorithms.optimizeBuyAndSell("buysell_in4.txt")));
        assertEquals(new Pair<>(1, 2), (JavaAlgorithms.optimizeBuyAndSell("buysell_in5.txt")));
        assertEquals(new Pair<>(null, null), (JavaAlgorithms.optimizeBuyAndSell("buysell_in6.txt")));
    }

    @Test
    void longestCommonSubstring() {
        assertEquals("АНКУ", JavaAlgorithms.longestCommonSubstring("КАНКУР", "ВАНКУВЕР"));
        assertEquals("КАН", JavaAlgorithms.longestCommonSubstring("КАН", "КАН"));
        assertEquals("КУР", JavaAlgorithms.longestCommonSubstring("КАНКУР", "ВЕКУР"));
        assertEquals("КАН", JavaAlgorithms.longestCommonSubstring("КАНКУР", "КАНАТ"));
        assertEquals("ЛЕД", JavaAlgorithms.longestCommonSubstring("КОЛЕДАСКОП", "ЛЕДОКОП"));
    }
}