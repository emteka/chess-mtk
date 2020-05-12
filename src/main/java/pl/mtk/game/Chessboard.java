package pl.mtk.game;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.pieces.*;
import pl.mtk.game.pieces.moves.MoveChecker;
import pl.mtk.websocket.SelectedTile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.mtk.game.Color.*;


@Getter
@Setter
public class Chessboard {

    private Tile[][] rows;

    private Chessboard() {
    }

    public static Chessboard getStandardChessboard() {
        Chessboard chessboard = new Chessboard();
        chessboard.fillWithTiles();
        chessboard.fillWithPieces();
        return chessboard;
    }

    public void fillWithTiles() {
        this.rows = new Tile[8][8];
        for (int row = 0; row < rows.length; row++) {
            for (int column = 0; column < rows[row].length ; column++) {
                if ((row + column) % 2 == 0){
                    rows[row][column] = Tile.whiteTile()
                            .setName(row, column);
                } else {
                    rows[row][column] = Tile.blackTile()
                            .setName(row, column);
                }
            }
        }
    }

    public void fillWithPieces(){
        this.fillWhitePieces();
        this.fillBlackPieces();
    }

    private void fillWhitePieces(){
        Tile[] whitePawnsRow = this.rows[1];
        Tile[] whitePiecesRow = this.rows[0];

        fillPawns(whitePawnsRow, WHITE);
        fillMajorPieces(whitePiecesRow, WHITE);
    }

    private void fillBlackPieces() {
        Tile[] blackPawnsRow = this.rows[6];
        Tile[] blackPiecesRow = this.rows[7];

        fillPawns(blackPawnsRow, BLACK);
        fillMajorPieces(blackPiecesRow, BLACK);
    }

    private void fillPawns(Tile[] pawnsRow, Color color) {
        Arrays.stream(pawnsRow)
                .forEach(tile -> tile.setPiece(new Pawn(color)));
    }

    private void fillMajorPieces(Tile[] tiles, Color color) {
        tiles[0].setPiece(new Rook(color));
        tiles[7].setPiece(new Rook(color));
        tiles[1].setPiece(new Knight(color));
        tiles[6].setPiece(new Knight(color));
        tiles[2].setPiece(new Bishop(color));
        tiles[5].setPiece(new Bishop(color));
        tiles[3].setPiece(new Queen(color));
        tiles[4].setPiece(new King(color));
    }

    public void selectTile(Tile tile) {
        tile.setSelected(true);
        Set<Tile> availableTiles = MoveChecker.checkMoves(this, tile);
        setAvailableTiles(availableTiles);
    }

    private void setAvailableTiles(Set<Tile> availableTiles) {
        availableTiles.forEach(tile -> tile.setAvailable(true));
    }

    public Tile getTile(SelectedTile selectedTile) {
        int row = mapToRow(selectedTile.getName().charAt(1));
        int column = mapToColumn(selectedTile.getName().charAt(0));
        return this.rows[row][column];
    }

    public static int mapToRow(char row) {
        return row - '1';
    }

    public static int mapToColumn(char column) {
        return Tile.columnToNumbers.get(column);
    }

    public boolean isAnyTileSelected() {
        return Arrays.stream(this.rows)
                .flatMap(Arrays::stream)
                .anyMatch(Tile::isSelected);
    }

    public Set<Tile> getAvailableTiles() {
        return Arrays.stream(this.rows)
                .flatMap(Arrays::stream)
                .filter(Tile::isAvailable)
                .collect(Collectors.toSet());
    }

    public void deselectAllTiles() {
        Arrays.stream(rows)
                .flatMap(Arrays::stream)
                .forEach(tile -> {  tile.setSelected(false);
                                    tile.setAvailable(false); });
    }

    private void move(Tile selectedTile, Tile destinationTile) {
        if (destinationTile.isAvailable()) {
            destinationTile.setPiece(selectedTile.getPiece());
            destinationTile.getPiece().setMoved(true);
            selectedTile.setPiece(null);
            deselectAllTiles();
        }
    }

    public void move(Tile destinationTile) {
        Arrays.stream(rows)
                .flatMap(Arrays::stream)
                .filter(Tile::isSelected)
                .forEach(selectedTile -> move(selectedTile, destinationTile));
    }
}
