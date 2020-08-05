package pl.mtk.game.analysis;

import pl.mtk.game.GameState;
import pl.mtk.game.chessboard.Chessboard;
import pl.mtk.game.chessboard.Tile;
import pl.mtk.game.pieces.moves.Move;

import java.util.Optional;
import java.util.function.Consumer;

public class Analysis {
    private GameState gameState;
    private Tile.Position clickedTile;

    public Analysis(GameState gameState, Tile.Position clickedTile) {
        this.gameState = gameState;
        this.clickedTile = clickedTile;
    }


    Consumer<Move> makeMove = move -> {
        move.make(gameState.getChessboard());
        gameState.getChessboard().deselectAllTiles();
    };

    public void start() {
        Chessboard chessboard = gameState.getChessboard();

        chessboard.getSelectedTile().ifPresentOrElse(
                selectedTile -> {
                    getChosenMove().ifPresentOrElse(
                            makeMove,
                            () -> gameState.getChessboard().deselectAllTiles());
                },
                () -> {
                    chessboard.deselectAllTiles();
                    chessboard.selectTile(clickedTile);
                }
        );
    }

    Optional<Move> getChosenMove() {
        return gameState.getChessboard()
                .getAvailableMoves()
                .stream()
                .filter(move -> move.getDestination().equals(clickedTile))
                .findFirst();
    }
}
