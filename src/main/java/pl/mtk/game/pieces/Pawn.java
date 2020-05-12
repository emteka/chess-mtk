package pl.mtk.game.pieces;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.Color;
import pl.mtk.game.pieces.moves.Move;

import java.util.ArrayList;
import java.util.List;

import static pl.mtk.game.pieces.moves.Move.Direction.*;

@Getter
@Setter
public class Pawn extends Piece {

    String symbol = "P";

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public List<List<Move>> allMoves() {
        List<List<Move>> moves = new ArrayList<>();
        Move.Direction moveDirection =
                this.getColor().equals(Color.WHITE) ? Move.Direction.UP : Move.Direction.DOWN;
        Move.Direction[] takeDirections =
                this.getColor().equals(Color.WHITE) ? new Move.Direction[] {UPLEFT, UPRIGHT} : new Move.Direction[] {DOWNLEFT, DOWNRIGHT};
        if (isMoved()) {
            moves.add(Move.getFriendlyMoves(1, moveDirection));
        } else {
            moves.add(Move.getFriendlyMoves(2, moveDirection));
        }
        moves.add(Move.getAttackingMoves(1, takeDirections[0]));
        moves.add(Move.getAttackingMoves(1, takeDirections[1]));
        return moves;
    }
}
