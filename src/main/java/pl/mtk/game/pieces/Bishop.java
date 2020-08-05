package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class Bishop extends Piece {

    private String symbol = "B";

    public Bishop(Color color) {
        super(color);
    }

}
