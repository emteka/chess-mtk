package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public class Knight extends Piece {

    private String symbol = "N";

    public Knight(Color color) {
        super(color);
    }

}
