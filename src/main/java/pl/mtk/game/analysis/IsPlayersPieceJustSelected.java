package pl.mtk.game.analysis;

import pl.mtk.game.Chessboard;
import pl.mtk.game.GameState;
import pl.mtk.game.Tile;
import pl.mtk.game.pieces.Piece;
import pl.mtk.websocket.SelectedTile;

public abstract class IsPlayersPieceJustSelected {
    public static void analyze(GameState gameState, SelectedTile tile) {
        Chessboard chessboard = gameState.getChessboard();
        Tile selectedTile = chessboard.getTile(tile);
        if (selectedTile.containsPiece()) {
            Piece piece = selectedTile.getPiece();
            boolean tileContainsThePieceOfPlayersColor = piece.getColor().equals(gameState.getTurn());
            if (tileContainsThePieceOfPlayersColor) {
                chessboard.selectTile(selectedTile);
            }
        }
    }
}
