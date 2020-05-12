package pl.mtk.game.analysis;

import pl.mtk.game.GameState;
import pl.mtk.websocket.SelectedTile;

public class Analysis {
    GameState gameState;
    SelectedTile tile;

    public Analysis(GameState gameState, SelectedTile tile) {
        this.gameState = gameState;
        this.tile = tile;
    }

    public void start() {
        IsAnyTileAlreadySelected.analyze(gameState, tile);
    }
}
