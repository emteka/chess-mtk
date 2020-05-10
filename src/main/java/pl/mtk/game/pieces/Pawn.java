package pl.mtk.game.pieces;

import lombok.Getter;
import lombok.Setter;

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
}
