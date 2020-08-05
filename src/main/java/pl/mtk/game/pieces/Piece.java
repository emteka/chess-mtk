package pl.mtk.game.pieces;

import lombok.Getter;
import pl.mtk.game.Color;

@Getter
public abstract class Piece {

    public Piece(Color color) {
        this.color = color;
    }

    Color color;
    int totalMoves;

    public void incrementMoves() {
        this.totalMoves = totalMoves + 1;
    }

}
