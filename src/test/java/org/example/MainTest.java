package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void testSelectAction(int input) throws WrongInputException {
        assertEquals(input, Main.selectAction(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4, 100, -100})
    void testSelectActionWrong(int input) {
        assertThrows(WrongInputException.class, () -> Main.selectAction(input));
    }
}