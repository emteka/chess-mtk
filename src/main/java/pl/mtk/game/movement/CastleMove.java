package pl.mtk.game;

import pl.mtk.game.pieces.Piece;


public class CastleMove {

    public CastleMove(Position absoluteDestination, Piece.PieceType helpingPiece, Position helpingPieceAbsoluteDestination) {
        this.absoluteDestination = absoluteDestination;
        this.helpingPiece = helpingPiece;
        this.helpingPieceAbsoluteDestination = helpingPieceAbsoluteDestination;
    }



    public final Position absoluteDestination;
    public final Piece.PieceType helpingPiece;
    public final Position helpingPieceAbsoluteDestination;

}
