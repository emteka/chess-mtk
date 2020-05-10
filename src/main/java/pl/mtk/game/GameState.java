package pl.mtk.game;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.analysis.Analysis;
import pl.mtk.websocket.SelectedTile;

@Setter
@Getter
public class GameState {

    public GameState() {
        this.chessboard = Chessboard.getStandardChessboard();
        this.turn = Turn.WHITE;
    }

    Chessboard chessboard;
    Turn turn;

    public void analyze(SelectedTile selectedTile) {
        Analysis analysis = new Analysis(this, selectedTile);
        analysis.start(this, selectedTile);
    }

}
