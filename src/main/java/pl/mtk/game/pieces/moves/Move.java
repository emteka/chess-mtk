package pl.mtk.game.pieces.moves;

import lombok.*;
import pl.mtk.game.Color;
import pl.mtk.game.chessboard.Chessboard;
import pl.mtk.game.chessboard.Tile;
import pl.mtk.game.pieces.Piece;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Move {

    private Tile.Position origin, destination;

    public void make(Chessboard chessboard) {
        Piece piece = chessboard.getTile(getOrigin())
                .getPiece();
        chessboard.getTile(getDestination())
                .setPiece(piece);
        piece.incrementMoves();
        chessboard.getTile(getOrigin()).clearPiece();
        chessboard.deselectAllTiles();
    }

//    public boolean isWithinBounds(Chessboard chessboard) {
//        return destination.getRow() >= 0 &&
//                destination.getRow() <= 7 &&
//                destination.getColumn() >= 0 &&
//                destination.getColumn() <= 7;
//    }

    public boolean isValid(Chessboard chessboard) {
        return  getDestination().getRow() >= 0 &&
                getDestination().getRow() <= 7 &&
                getDestination().getColumn() >= 0 &&
                getDestination().getColumn() <= 7;
    }

    public boolean notContainsPieceOfSameColor(Chessboard chessboard) {
        boolean containsPiece = chessboard.getTile(getDestination()).containsPiece();
        if (containsPiece) {
            return chessboard.getTile(getDestination()).getPiece().getColor()
                    .equals(chessboard.getTile(getOrigin()).getColor());
        }
        return true;
    }

}
