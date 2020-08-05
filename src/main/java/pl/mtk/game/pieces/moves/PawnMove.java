package pl.mtk.game.pieces.moves;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.mtk.game.Color;
import pl.mtk.game.chessboard.Chessboard;
import pl.mtk.game.chessboard.Tile;

@Getter
@Setter
@ToString
public class PawnMove extends Move {

    public PawnMove(Tile.Position start, Tile.Position destination) {
        super(start, destination);
    }

    public PawnMove() {
        super();
    }

    private Tile.Position origin, destination;

//    @Override
//    public boolean isWithinBounds(Chessboard chessboard) {
//        return getDestination().getRow() >= 0 &&
//                getDestination().getRow() <= 7 &&
//                getDestination().getColumn() >= 0 &&
//                getDestination().getColumn() <= 7;
//    }

    @Override
    public boolean isValid(Chessboard chessboard) {
        boolean isWithinBounds = this.destination.getRow() >= 0 &&
                this.destination.getRow() <= 7 &&
                this.destination.getColumn() >= 0 &&
                this.destination.getColumn() <= 7;
        if (isWithinBounds) {
            boolean destinationContainsPiece = chessboard.getTile(this.destination).containsPiece();
            return !destinationContainsPiece;
        }
        return false;
    }

}
