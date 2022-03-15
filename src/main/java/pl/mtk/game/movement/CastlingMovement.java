package pl.mtk.game.movement;

import pl.mtk.game.CastleMove;
import pl.mtk.game.Position;
import pl.mtk.game.pieces.Piece;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CastlingMovement implements Movement {

    public final Position absoluteDestination;
    public final Piece.PieceType helpingPiece;
    public final Position helpingPieceAbsoluteDestination;

    private CastlingMovement(Position absoluteDestination, Piece.PieceType helpingPiece, Position helpingPieceAbsoluteDestination) {
        this.absoluteDestination = absoluteDestination;
        this.helpingPiece = helpingPiece;
        this.helpingPieceAbsoluteDestination = helpingPieceAbsoluteDestination;
    }

    public static Set<Movement> castlingMovement(CastleMove... castleMoves) {
        return Arrays.stream(castleMoves)
                .map(move -> new CastlingMovement(move.absoluteDestination, move.helpingPiece, move.helpingPieceAbsoluteDestination))
                .collect(Collectors.toSet());
    }

}
