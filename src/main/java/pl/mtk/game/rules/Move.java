package pl.mtk.game.rules;

import pl.mtk.game.Gameboard;
import pl.mtk.game.Position;

import java.util.HashMap;

public class Move {

    Position[] origin;
    Position[] destination;

    public void make(Gameboard gameboard)  {
        HashMap<Position, Position> map = new HashMap<>();

        for (int i = 0; i < origin.length; i++) {
            map.put(origin[i], destination[i]);
        }

        map.entrySet()
                .forEach(
                        e -> {
                            try {
                                gameboard.position(e.getValue())
                                        .place(
                                                gameboard.position(e.getKey()).lift()
                                        );
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                );
    }

}