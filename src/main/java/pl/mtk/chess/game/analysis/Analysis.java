package pl.mtk.chess.game.analysis;

import pl.mtk.chess.game.GameState;
import pl.mtk.chess.websocket.SelectedTile;

public class Analysis {
    GameState gameState;
    SelectedTile selectedTile;

    public Analysis(GameState gameState, SelectedTile selectedTile) {
        this.gameState = gameState;
        this.selectedTile = selectedTile;
    }

    public void start(GameState gameState, SelectedTile selectedTile) {
        IsAnyTileAlreadySelected.analyze(gameState, selectedTile);
    }
}
