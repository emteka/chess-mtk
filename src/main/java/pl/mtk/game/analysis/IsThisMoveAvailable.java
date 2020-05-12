package pl.mtk.game.analysis;

import pl.mtk.game.Chessboard;
import pl.mtk.game.GameState;
import pl.mtk.game.Tile;
import pl.mtk.websocket.SelectedTile;

import java.util.Set;

public class IsThisMoveAvailable {
    public static void analyze(GameState gameState, SelectedTile tile) {
        Chessboard chessboard = gameState.getChessboard();
        Set<Tile> moves = chessboard.getAvailableTiles();
        Tile selectedTile = chessboard.getTile(tile);
        if (moves.contains(selectedTile)) {
            chessboard.move(selectedTile);
            gameState.nextTurn();
        }
        chessboard.deselectAllTiles();
    }
}
