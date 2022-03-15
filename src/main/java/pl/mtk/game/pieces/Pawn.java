package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.PAWN;

public class Pawn extends Piece {
    private Pawn(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Pawn pawn(Color color) {
        return new Pawn(PAWN, color);
    }
}
