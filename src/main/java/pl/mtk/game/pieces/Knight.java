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
public class Knight extends Piece {

    String symbol = "N";

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<List<Move>> allMoves() {
        return Move.getKnightsMoves();
    }
}
