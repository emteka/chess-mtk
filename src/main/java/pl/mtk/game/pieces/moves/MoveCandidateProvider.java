package pl.mtk.game.pieces.moves;

import org.springframework.stereotype.Service;
import pl.mtk.game.pieces.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static pl.mtk.game.Color.WHITE;
import static pl.mtk.game.pieces.moves.MoveCandidate.pieceMove;
import static pl.mtk.game.pieces.moves.Vector.Direction.*;


@Service
public class MoveCandidateProvider {

    private static final Map<Class, Function<Piece, Set<List<MoveCandidate>>>> PIECES = Map.of(
            Pawn.class, MoveCandidateProvider::generatePawnMoves,
            Knight.class, MoveCandidateProvider::generateKnightMoves,
            Bishop.class, MoveCandidateProvider::generateBishopMoves,
            Rook.class, MoveCandidateProvider::generateRookMoves,
            Queen.class, MoveCandidateProvider::generateQueenMoves,
            King.class, MoveCandidateProvider::generateKingMoves
    );

    private static final Function<Vector, MoveCandidate> PAWN_ATTACKING_MOVE_CANDIDATE = v -> new MoveCandidate(new PawnAttackingMove(), v);
    private static final Function<Vector, MoveCandidate> PAWN_MOVE_CANDIDATE = v -> new MoveCandidate(new PawnMove(), v);
    private static final Function<Vector, MoveCandidate> MOVE_CANDIDATE = MoveCandidate::pieceMove;

    public static Set<List<MoveCandidate>> getMoveCandidates(Piece piece) {
        return PIECES.get(piece.getClass()).apply(piece);
    }

    private static Set<List<MoveCandidate>> generatePawnMoves(Piece pawn) {
        boolean isWhite = pawn.getColor().equals(WHITE);
        boolean firstMove = pawn.getTotalMoves() == 0;

        return Set.of(
                generateMoveCandidates(isWhite ? UPLEFT : DOWNLEFT, 1, PAWN_ATTACKING_MOVE_CANDIDATE),
                generateMoveCandidates(isWhite ? UPRIGHT : DOWNRIGHT, 1, PAWN_ATTACKING_MOVE_CANDIDATE),
                generateMoveCandidates(isWhite ? UP : DOWN, firstMove ? 2 : 1, PAWN_MOVE_CANDIDATE)
        );
    }

    private static Set<List<MoveCandidate>> generateKnightMoves(Piece knight) {
        return Set.of(
                List.of(pieceMove(new Vector(1, 2))),
                List.of(pieceMove(new Vector(-1, 2))),
                List.of(pieceMove(new Vector(1, -2))),
                List.of(pieceMove(new Vector(-1, -2))),
                List.of(pieceMove(new Vector(2, 1))),
                List.of(pieceMove(new Vector(-2, 1))),
                List.of(pieceMove(new Vector(2, -1))),
                List.of(pieceMove(new Vector(-2, -1)))
        );
    }

    private static Set<List<MoveCandidate>> generateBishopMoves(Piece piece) {
        return diagonals(8);
    }

    private static Set<List<MoveCandidate>> generateRookMoves(Piece piece) {
        return horizontalAndVertical(8);
    }

    private static Set<List<MoveCandidate>> generateQueenMoves(Piece piece) {
        return Stream.concat(
                horizontalAndVertical(8).stream(),
                diagonals(8).stream())
                .collect(Collectors.toSet());
    }

    private static Set<List<MoveCandidate>> generateKingMoves(Piece piece) {
        return Stream.concat(
                horizontalAndVertical(1).stream(),
                diagonals(1).stream())
                .collect(Collectors.toSet());
    }

    private static List<MoveCandidate> generateMoveCandidates(Vector.Direction direction, int range, Function<Vector, MoveCandidate> lambda) {
        return IntStream.rangeClosed(1, range)
                .boxed()
                .map(number -> new Vector(direction).scale(number))
                .map(lambda)
                .collect(Collectors.toList());
    }

    private static Set<List<MoveCandidate>> diagonals(int range) {
        return Set.of(
            generateMoveCandidates(DOWNLEFT, range, MOVE_CANDIDATE),
            generateMoveCandidates(DOWNRIGHT, range, MOVE_CANDIDATE),
            generateMoveCandidates(UPLEFT, range, MOVE_CANDIDATE),
            generateMoveCandidates(UPRIGHT, range, MOVE_CANDIDATE));
    }

    private static Set<List<MoveCandidate>> horizontalAndVertical(int range) {
        return Set.of(
            generateMoveCandidates(DOWN, range, MOVE_CANDIDATE),
            generateMoveCandidates(RIGHT, range, MOVE_CANDIDATE),
            generateMoveCandidates(UP, range, MOVE_CANDIDATE),
            generateMoveCandidates(LEFT, range, MOVE_CANDIDATE));
    }

}
