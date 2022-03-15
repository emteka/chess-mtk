package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.ROOK;

public class Rook extends Piece {

    private Rook(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Rook rook(Color color) {
        return new Rook(ROOK, color);
    }

}
