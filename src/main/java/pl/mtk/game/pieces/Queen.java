package pl.mtk.game.pieces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Queen extends Piece {

    String symbol = "Q";

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
