package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class Pawn extends Piece {

    private String symbol = "P";

    public Pawn(Color color) {
        super(color);
    }

}
