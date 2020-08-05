package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class King extends Piece {

    private String symbol = "K";

    public King(Color color) {
        super(color);
    }

}
