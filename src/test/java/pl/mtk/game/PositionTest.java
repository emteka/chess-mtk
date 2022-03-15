package pl.mtk.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pl.mtk.game.Position.position;

class PositionTest {

    @Test
    void shouldConvertStringToPosition() {
        String position1 = "A1";
        String position2 = "B4";

        assertEquals(0, position(position1).rank);
        assertEquals(0, position(position1).file);
        assertEquals(3, position(position2).rank);
        assertEquals(1, position(position2).file);
    }

}