package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class Queen extends Piece {

    private String symbol = "Q";

    public Queen(Color color) {
        super(color);
    }

}
