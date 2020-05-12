package pl.mtk.game.pieces;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.Color;
import pl.mtk.game.pieces.moves.Move;

import java.util.ArrayList;
import java.util.List;

import static pl.mtk.game.pieces.moves.Move.Direction.*;
import static pl.mtk.game.pieces.moves.Move.Direction.DOWNRIGHT;

@Getter
@Setter
public class King extends Piece {

    String symbol = "K";

    public King(Color color) {
        super(color);
    }

    @Override
    public List<List<Move>> allMoves() {
        List<List<Move>> moves = new ArrayList<>();
        List<Move.Direction> moveDirection = List.of(UP, UPLEFT, LEFT, UPRIGHT, RIGHT, DOWNLEFT, DOWN, DOWNRIGHT);
        moveDirection.forEach(direction -> moves.add(Move.getMoves(1, direction)));
        return moves;
    }
}
