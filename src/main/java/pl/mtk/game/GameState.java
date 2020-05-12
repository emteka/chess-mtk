package pl.mtk.game;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.analysis.Analysis;
import pl.mtk.websocket.SelectedTile;

import static pl.mtk.game.Color.*;
@Setter
@Getter
public class GameState {

    public GameState() {
        this.chessboard = Chessboard.getStandardChessboard();
        this.turn = WHITE;
    }

    Chessboard chessboard;
    Color turn;

    public void analyze(SelectedTile selectedTile) {
        Analysis analysis = new Analysis(this, selectedTile);
        analysis.start();
    }

    public void nextTurn() {
        this.setTurn(this.turn.equals(WHITE) ? BLACK : WHITE);
    }

}
