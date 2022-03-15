package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.BISHOP;

public class Bishop extends Piece {
    private Bishop(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Bishop bishop(Color color) {
        return new Bishop(BISHOP, color);
    }
}
