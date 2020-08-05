package pl.mtk.game.chessboard;

import pl.mtk.game.Color;
import pl.mtk.game.pieces.*;
import pl.mtk.websocket.SelectedTile;

import java.util.Arrays;
import java.util.Map;

import static java.util.Map.entry;
import static pl.mtk.game.Color.BLACK;
import static pl.mtk.game.Color.WHITE;

public class ChessboardUtils {

    public static final Map<String, Integer> lettersToColumn = Map.ofEntries(
            entry("A",0),
            entry("B",1),
            entry("C",2),
            entry("D",3),
            entry("E",4),
            entry("F",5),
            entry("G",6),
            entry("H",7));

    public static void fillWithTiles(Chessboard chessboard) {
        chessboard.setTiles(new Tile[8][8]);
        Tile[][] tiles = chessboard.getTiles();

        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[row].length ; column++) {
                tiles[row][column] = Tile.createTile(new Tile.Position(row, column));
            }
        }
    }

    public static void fillWithPieces(Chessboard chessboard){
        fillWhitePieces(chessboard);
        fillBlackPieces(chessboard);
    }

    private static void fillWhitePieces(Chessboard chessboard){
        Tile[] whitePiecesRow = chessboard.getRow(0);
        Tile[] whitePawnsRow = chessboard.getRow(1);

        fillPawns(whitePawnsRow, WHITE);
        fillMajorPieces(whitePiecesRow, WHITE);
    }

    private static void fillBlackPieces(Chessboard chessboard) {
        Tile[] blackPawnsRow = chessboard.getRow(6);
        Tile[] blackPiecesRow = chessboard.getRow(7);

        fillPawns(blackPawnsRow, BLACK);
        fillMajorPieces(blackPiecesRow, BLACK);
    }

    private static void fillPawns(Tile[] pawnsRow, Color color) {
        Arrays.stream(pawnsRow)
                .forEach(tile -> tile.setPiece(new Pawn(color)));
    }

    private static void fillMajorPieces(Tile[] tiles, Color color) {
        tiles[0].setPiece(new Rook(color));
        tiles[7].setPiece(new Rook(color));
        tiles[1].setPiece(new Knight(color));
        tiles[6].setPiece(new Knight(color));
        tiles[2].setPiece(new Bishop(color));
        tiles[5].setPiece(new Bishop(color));
        tiles[3].setPiece(new Queen(color));
        tiles[4].setPiece(new King(color));
    }

    public static Tile.Position mapToPosition(SelectedTile selectedTile) {
        int column = lettersToColumn.get(selectedTile.getName().substring(0, 1));
        int row = Integer.parseInt(selectedTile.getName().substring(1)) - 1;
        return new Tile.Position(row, column);
    }
}
