package pl.mtk.game.chessboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.pieces.moves.Move;
import pl.mtk.game.pieces.moves.MoveFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class Chessboard {

    private Tile[][] tiles;
    private Set<Move> availableMoves;
    private Set<Move> allMoves;

    private Chessboard() {
    }

    public static Chessboard getStandardChessboard() {
        Chessboard chessboard = new Chessboard();
        ChessboardUtils.fillWithTiles(chessboard);
        ChessboardUtils.fillWithPieces(chessboard);
        return chessboard;
    }

    public Tile[] getRow(int i) {
        return tiles[i];
    }

    public Tile getTile(Tile.Position position) {
        return this.tiles[position.getRow()][position.getColumn()];
    }

    public boolean isAnyTileSelected() {
        return Arrays.stream(this.tiles)
                .flatMap(Arrays::stream)
                .anyMatch(Tile::isSelected);
    }
    @JsonIgnore
    public Tile.Position getSelectedTilePosition() {
        return Arrays.stream(this.tiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isSelected)
                .findAny()
                .map(Tile::getPosition)
                .orElseThrow(() -> new IllegalStateException("No tile selected"));
    }

    public void selectTile(Tile.Position position) {
        Tile tile = getTile(position);
        tile.setSelected(true);
        if (tile.containsPiece()) {
            availableMoves = MoveFactory.getMoves(this, position);
            setAvailableTiles();
        }
    }


    public void deselectAllTiles() {
        Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .forEach(tile -> {  tile.setSelected(false);
                                    tile.setAvailable(false); });
    }

    public Set<Tile> getAvailableTiles() {
        return Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isAvailable)
                .collect(Collectors.toSet());
    }

    public void setAvailableTiles() {
        availableMoves.stream()
                .filter(move -> move.getOrigin().equals(getSelectedTilePosition()))
                .map(move -> getTile(move.getDestination()))
                .forEach(tile -> tile.setAvailable(true));
    }

    public Optional<Tile> getChosenAvailableTile(Tile.Position position) {
        return getAvailableTiles().stream()
                .filter(availableTile -> availableTile.getPosition().equals(position))
                .findAny();
    }

    public void makeMove(Tile.Position position) {
        getAvailableMoves().stream()
                .filter(move -> move.getOrigin().equals(getSelectedTilePosition()) && move.getDestination().equals(position))
                .findAny()
                .ifPresent(move -> move.make(this));
    }


    public Optional<Tile> getSelectedTile() {
        return Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isSelected)
                .findFirst();
    }
}
