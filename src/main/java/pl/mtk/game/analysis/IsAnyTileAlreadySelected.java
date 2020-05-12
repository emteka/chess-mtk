package pl.mtk.game.analysis;

import pl.mtk.game.Chessboard;
import pl.mtk.game.GameState;
import pl.mtk.websocket.SelectedTile;

public abstract class IsAnyTileAlreadySelected {
    public static void analyze(GameState gameState, SelectedTile tile) {
        Chessboard chessboard = gameState.getChessboard();
        if (chessboard.isAnyTileSelected()) {
            IsThisMoveAvailable.analyze(gameState, tile);
        }
        IsPlayersPieceJustSelected.analyze(gameState, tile);
    }
}
