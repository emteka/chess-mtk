package pl.mtk.game.rules;

import pl.mtk.Game;
import pl.mtk.game.*;
import pl.mtk.game.movement.Movement;
import pl.mtk.game.pieces.Piece;

import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Map.entry;
import static pl.mtk.game.Color.*;
import static pl.mtk.game.MoveDirection.*;
import static pl.mtk.game.MoveShape.L_SHAPE;
import static pl.mtk.game.Position.position;
import static pl.mtk.game.pieces.Bishop.bishop;
import static pl.mtk.game.pieces.King.king;
import static pl.mtk.game.pieces.Knight.knight;
import static pl.mtk.game.pieces.Pawn.pawn;
import static pl.mtk.game.pieces.Piece.PieceType.*;
import static pl.mtk.game.pieces.Queen.queen;
import static pl.mtk.game.pieces.Rook.rook;
import static pl.mtk.game.movement.CastlingMovement.castlingMovement;
import static pl.mtk.game.movement.LeapMovement.leapMovement;
import static pl.mtk.game.movement.DirectionalMovement.directionalMovement;

public class ClassicRules implements Rules {

    public final int gameboardSize = 8;

    @Override
    public Gameboard board() {
        return GameboardGenerator
                .newGameboard()
                .ofSize(this.gameboardSize)
                .generate();
    }

    @Override
    public Map<Position, Piece> piecesSetup() {
        return Map.ofEntries(
                entry(position("A1"), rook(WHITE)),
                entry(position("A1"), rook(WHITE)),
                entry(position("B1"), knight(WHITE)),
                entry(position("C1"), bishop(WHITE)),
                entry(position("D1"), queen(WHITE)),
                entry(position("E1"), king(WHITE)),
                entry(position("F1"), bishop(WHITE)),
                entry(position("G1"), knight(WHITE)),
                entry(position("H1"), rook(WHITE)),
                entry(position("A2"), pawn(WHITE)),
                entry(position("B2"), pawn(WHITE)),
                entry(position("C2"), pawn(WHITE)),
                entry(position("D2"), pawn(WHITE)),
                entry(position("E2"), pawn(WHITE)),
                entry(position("F2"), pawn(WHITE)),
                entry(position("G2"), pawn(WHITE)),
                entry(position("H2"), pawn(WHITE)),
                entry(position("A8"), rook(BLACK)),
                entry(position("B8"), knight(BLACK)),
                entry(position("C8"), bishop(BLACK)),
                entry(position("D8"), queen(BLACK)),
                entry(position("E8"), king(BLACK)),
                entry(position("F8"), bishop(BLACK)),
                entry(position("G8"), knight(BLACK)),
                entry(position("H8"), rook(BLACK)),
                entry(position("A7"), pawn(BLACK)),
                entry(position("B7"), pawn(BLACK)),
                entry(position("C7"), pawn(BLACK)),
                entry(position("D7"), pawn(BLACK)),
                entry(position("E7"), pawn(BLACK)),
                entry(position("F7"), pawn(BLACK)),
                entry(position("G7"), pawn(BLACK)),
                entry(position("H7"), pawn(BLACK))
        );
    }

    BiPredicate<Game, Piece> notTouched = (k, v) -> true;
    BiPredicate<Game, Piece> notInCheck = (k, v) -> true;
    BiPredicate<Game, Piece> kingDoesNotCrossAttackedSquare = (k, v) -> true;
    BiPredicate<Game, Piece> notOccupied = (k, v) -> true;
    BiPredicate<Game, Piece> occupiedByEnemyPiece = (k, v) -> true;
    BiPredicate<Object, Object> noConditions = (x, y) -> true;
    BiPredicate<Object, Object> enPassant = (x, y) -> true;

    Function<Piece, MoveDirection> forward = piece -> piece.color == WHITE ? UP : DOWN;
    Function<Piece, Set<MoveDirection>> forwardDiagonals = piece -> piece.color == WHITE ? Set.of(UPRIGHT, UPLEFT) : Set.of(DOWNRIGHT, DOWNLEFT);
    Function<Piece, CastleMove> shortCastle = piece -> piece.color == WHITE ? new CastleMove(position("B1"), ROOK, position("C1")) : new CastleMove(position("B8"), ROOK, position("C8"));
    Function<Piece, CastleMove> longCastle = piece -> piece.color == WHITE ? new CastleMove(position("F1"), ROOK, position("E1")) : new CastleMove(position("F8"), ROOK, position("E8"));

    @Override
    public Map<Set<Movement>, BiPredicate> movement(Piece piece) {
        return switch (piece.pieceType) {
            case ROOK   -> Map.of(
                directionalMovement(gameboardSize, UP, RIGHT, DOWN, LEFT),
                noConditions);
            case KNIGHT -> Map.of(
                leapMovement(L_SHAPE),
                noConditions);
            case BISHOP -> Map.of(
                directionalMovement(gameboardSize, UPRIGHT, DOWNRIGHT, DOWNLEFT, UPLEFT),
                noConditions);
            case QUEEN  -> Map.of(
                directionalMovement(gameboardSize, UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT, UPLEFT),
                noConditions);
            case KING   -> Map.of(
                directionalMovement(1, UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT, UPLEFT),
                noConditions,
                castlingMovement(shortCastle.apply(piece), longCastle.apply(piece)),
                notInCheck.and(notTouched.and(kingDoesNotCrossAttackedSquare)));
            case PAWN -> Map.of(
                    directionalMovement(1, forward.apply(piece)),
                    notOccupied,
                    directionalMovement(2, forward.apply(piece)),
                    notTouched.and(notOccupied),
                    directionalMovement(1, forwardDiagonals.apply(piece)),
                    occupiedByEnemyPiece.or(enPassant)
            );
        };
    }



    @Override
    public Map<Result, Predicate<Game>> endgameConditions() {
        Predicate<Game> kingIsInCheck = game -> false;
        Predicate<Game> theresNoMoves = game -> false;

        return Map.of(
                Result.WIN_CHECKMATE, kingIsInCheck.and(theresNoMoves),
                Result.DRAW_STALEMATE, theresNoMoves

        );
    }

}
