package pl.mtk.game;

import lombok.Getter;
import pl.mtk.game.analysis.Analysis;
import pl.mtk.game.chessboard.Chessboard;
import pl.mtk.game.chessboard.ChessboardUtils;
import pl.mtk.game.chessboard.Tile;
import pl.mtk.websocket.SelectedTile;

import static pl.mtk.game.Color.*;
import static pl.mtk.game.chessboard.Chessboard.getStandardChessboard;

@Getter
public class GameState {

    public static GameState startNewGame() {
        GameState gameState = new GameState(getStandardChessboard(), WHITE);
//        Set<Move> moves = MoveProvider.getAllMoves(gameState);
//        gameState.getChessboard().setMoves(moves);
        return gameState;
    }

    private GameState(Chessboard chessboard, Color turn) {
        this.chessboard = getStandardChessboard();
        this.turn = WHITE;
    }

    Chessboard chessboard;
    Color turn;

    public void analyze(SelectedTile selectedTile) {
        Tile.Position position = ChessboardUtils.mapToPosition(selectedTile);
        Analysis analysis = new Analysis(this, position);
        analysis.start();
    }

    public void nextTurn() {
        this.turn = (this.turn.equals(WHITE) ? BLACK : WHITE);
//        chessboard.setMoves(MoveProvider.getAllMoves(this));
    }

}
