package pl.mtk.game.analysis;

import pl.mtk.game.GameState;
import pl.mtk.websocket.SelectedTile;

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
