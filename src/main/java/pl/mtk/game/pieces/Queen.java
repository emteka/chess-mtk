package pl.mtk.game.pieces;

import pl.mtk.game.Color;

import static pl.mtk.game.pieces.Piece.PieceType.QUEEN;

public class Queen extends Piece {
    private Queen(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static Queen queen(Color color) {
        return new Queen(QUEEN, color);
    }
}
