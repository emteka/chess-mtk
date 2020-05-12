package pl.mtk.game.pieces.moves;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.Chessboard;
import pl.mtk.game.Tile;

import java.util.*;

@Getter
@Setter
public class MoveChecker {
    public static Set<Tile> checkMoves(Chessboard chessboard, Tile selectedTile) {
        List<List<Move>> allMoves = selectedTile.getPiece().allMoves();
        Set<Tile> finalMoves = new HashSet<>();

        Tile[][] board = chessboard.getRows();

        Tile.Position tilePosition = selectedTile.getPosition();

        for (List<Move> oneDirectionList : allMoves) {
            for (Move move : oneDirectionList) {
                int moveRow = tilePosition.getRow() + move.getVertical();
                int moveColumn = tilePosition.getColumn() + move.getHorizontal();
                if (isInBounds(moveRow, moveColumn, board)) {
                    Tile destinationTile = board[moveRow][moveColumn];
                    if (move.isFriendly() && !destinationTile.containsPiece()) {
                        finalMoves.add(destinationTile);
                    }
                    if (move.isAttacking() && destinationTile.containsPiece()) {
                        if (selectedTile.getPiece().getColor().equals(destinationTile.getPiece().getColor())) {
                            break;
                        }
                        finalMoves.add(destinationTile);

                    }
                }

            }
        }
        return finalMoves;
    }

    private static boolean isInBounds(int moveRow, int moveColumn, Tile[][] board) {
        return (moveRow >= 0) && (moveRow < board.length)
                && (moveColumn >= 0) && (moveColumn < board[0].length);
    }
}
