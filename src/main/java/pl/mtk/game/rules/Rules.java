package pl.mtk.game.rules;

import pl.mtk.Game;
import pl.mtk.game.Gameboard;
import pl.mtk.game.Position;
import pl.mtk.game.movement.Movement;
import pl.mtk.game.pieces.Piece;

import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Rules {

    Gameboard board();
    Map<Position, Piece> piecesSetup();
    Map<Set<Movement>, BiPredicate> movement(Piece piece);

    Set<Predicate<Game>> endgameConditions();
}
