package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testSelectAction() throws WrongInputException {
        assertEquals(1, Main.selectAction(1));
        assertEquals(2, Main.selectAction(2));
        assertEquals(3, Main.selectAction(3));
    }

    @Test
    void testSelectActionWrong() {
        assertThrows(WrongInputException.class, () -> Main.selectAction(-1));
        assertThrows(WrongInputException.class, () -> Main.selectAction(4));
    }
}
