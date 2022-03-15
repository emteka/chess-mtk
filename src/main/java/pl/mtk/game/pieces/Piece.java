package pl.mtk.game.pieces;

import pl.mtk.game.Color;

public abstract class Piece {

    public enum PieceType {
        ROOK('R'),
        KNIGHT('N'),
        BISHOP('B'),
        QUEEN('Q'),
        KING('K'),
        PAWN('P');

        public final char symbol;

        PieceType(char symbol) {
            this.symbol = symbol;
        }
    }

    public final PieceType pieceType;
    public final Color color;

    Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }
}
