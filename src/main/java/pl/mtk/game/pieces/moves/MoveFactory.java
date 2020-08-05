package pl.mtk.game.pieces.moves;

import pl.mtk.game.chessboard.Chessboard;
import pl.mtk.game.chessboard.Tile;
import pl.mtk.game.pieces.*;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveFactory {

    public static Set<Move> getMoves(Chessboard chessboard, Tile.Position origin) {
        Piece piece = chessboard.getTile(origin).getPiece();

        return MoveCandidateProvider
                .getMoveCandidates(piece).stream()
                .map(toMoveListStream(origin))
                .map(moves -> moves.stream().filter(move -> move.isValid(chessboard)).collect(Collectors.toList()))
                .flatMap(getPossibleMoves(chessboard))
                .filter(move -> move.notContainsPieceOfSameColor(chessboard))
                .collect(Collectors.toSet());
    }

    private static Function<List<MoveCandidate>, List<Move>> toMoveListStream(Tile.Position origin) {
        return moveCandidateList -> moveCandidateList.stream()
                .map(moveCandidate -> moveCandidate.toMove(origin))
                .collect(Collectors.toList());
    }

    private static Function<List<Move>, Stream<Move>> getPossibleMoves(Chessboard chessboard) {

        return moves -> {
            Predicate<Move> tileContainsPiece = move -> !chessboard.getTile(move.getDestination()).containsPiece();

            System.out.println(moves.stream()
                    .map(move -> Tile.findName(move.getDestination()))
                    .collect(Collectors.joining(", ")));

            return Stream.concat(
                    moves.stream()
                            .takeWhile(tileContainsPiece)
                    .peek(move -> System.out.println(Tile.findName(move.getOrigin()) + " -> " + Tile.findName(move.getDestination()) + " destination occupied: " + chessboard.getTile(move.getDestination()).containsPiece())),
                    moves.stream()
                            .dropWhile(tileContainsPiece)
                            .limit(1)
                            .peek(move -> System.out.println(Tile.findName(move.getOrigin()) + " -> " + Tile.findName(move.getDestination()) + " destination occupied: " + chessboard.getTile(move.getDestination()).containsPiece()))
            );
        };
    }

}
