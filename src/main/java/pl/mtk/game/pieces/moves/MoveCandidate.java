package pl.mtk.game.pieces.moves;

import lombok.Getter;
import pl.mtk.game.chessboard.Tile;

@Getter
public final class MoveCandidate {

    public MoveCandidate(Move move, Vector vector) {
        this.move = move;
        this.vector = vector;
    }

    Move move;
    Vector vector;

    public static MoveCandidate pieceMove(Vector vector) {
        return new MoveCandidate(new Move(), vector);
    }

    public static MoveCandidate pawnMove(Vector vector) {
        return new MoveCandidate(new PawnMove(), vector);
    }

    public static MoveCandidate pawnAttackingMove(Vector vector) {
        return new MoveCandidate(new PawnAttackingMove(), vector);
    }

    public Move toMove(Tile.Position origin) {
        Move move = this.getMove();
        move.setOrigin(origin);
        move.setDestination(this.getVector().toDestination(origin));
        return move;
    }

}
