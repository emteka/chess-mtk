package pl.mtk;

import pl.mtk.game.Color;
import pl.mtk.game.Gameboard;
import pl.mtk.game.rules.Rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

    Rules rules;
    Gameboard gameboard;
    Color turn;


    public Set<MoveEvent> possibleMoves = new HashSet<>();
    public List<Move> moveHistory = new ArrayList<>();

    public Game(Rules rules) {

    }

    public Game makeMove(MoveEvent move) {
        return null;
    }
}
