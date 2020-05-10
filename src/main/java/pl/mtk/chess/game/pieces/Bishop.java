package pl.mtk.chess.game.pieces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bishop extends Piece {

    String symbol = "B";

    public Bishop(Piece.Color color) {
        super(color);
    }
    @Override
    public String toString() {
        return "Bishop";
    }
}
