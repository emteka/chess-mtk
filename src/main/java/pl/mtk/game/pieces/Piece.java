package pl.mtk.game.pieces;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.Color;
import pl.mtk.game.pieces.moves.Move;

import java.util.List;

@Getter
@Setter
public abstract class Piece {

    public Piece(Color color) {
        this.color = color;
    }

    public abstract List<List<Move>> allMoves();

    Color color;
    boolean moved;
    List<List<Move>> moves;
}
