package pl.mtk.game.analysis;

import pl.mtk.game.Chessboard;
import pl.mtk.game.GameState;
import pl.mtk.game.Tile;
import pl.mtk.websocket.SelectedTile;

import java.util.Set;

public class IsThisMoveAvailable {
    public static void analyze(GameState gameState, SelectedTile selectedTile) {
        Chessboard chessboard = gameState.getChessboard();
        Set<Tile> moves = chessboard.getAvailableTiles();
        Tile selected = chessboard.getTile(selectedTile);
        if (moves.contains(selected)) {
            chessboard.move(selectedTile);
        }
        chessboard.deselect();
    }
}
