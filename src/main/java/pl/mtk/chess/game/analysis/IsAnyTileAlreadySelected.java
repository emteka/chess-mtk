package pl.mtk.chess.game.analysis;

import pl.mtk.chess.game.Chessboard;
import pl.mtk.chess.game.GameState;
import pl.mtk.chess.websocket.SelectedTile;

public abstract class IsAnyTileAlreadySelected {
    public static void analyze(GameState gameState, SelectedTile selectedTile) {
        Chessboard chessboard = gameState.getChessboard();
        if (chessboard.isAnyTileSelected()) {
            IsThisMoveAvailable.analyze(gameState, selectedTile);
        }
        IsPlayersPieceJustSelected.analyze(gameState, selectedTile);
    }
}
