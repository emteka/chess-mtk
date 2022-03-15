package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.KING;

public class King extends Piece {
    private King(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static King king(Color color) {
        return new King(KING, color);
    }
}
