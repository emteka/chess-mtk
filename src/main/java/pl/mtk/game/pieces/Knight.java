package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.KNIGHT;

public class Knight extends Piece {
    private Knight(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Knight knight(Color color) {
        return new Knight(KNIGHT, color);
    }
}
