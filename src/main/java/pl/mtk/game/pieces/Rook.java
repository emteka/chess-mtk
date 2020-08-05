package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class Rook extends Piece {

    private String symbol = "R";

    public Rook(Color color) {
        super(color);
    }

}
