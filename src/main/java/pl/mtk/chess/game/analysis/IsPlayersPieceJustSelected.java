package pl.mtk.chess.game.analysis;

import pl.mtk.chess.game.Chessboard;
import pl.mtk.chess.game.GameState;
import pl.mtk.chess.game.pieces.Piece;
import pl.mtk.chess.websocket.SelectedTile;

public abstract class IsPlayersPieceJustSelected {
    public static void analyze(GameState gameState, SelectedTile selectedTile) {
        Chessboard chessboard = gameState.getChessboard();
        Piece piece = chessboard.getTile(selectedTile).getPiece();
        boolean tileContainsPiece = piece != null;
        if (tileContainsPiece) {
            boolean tileContainsThePieceOfPlayersColor = piece.getColor().name().equals(gameState.getTurn().name());
            if (tileContainsThePieceOfPlayersColor) {
                chessboard.selectTile(selectedTile);
            }
        }
    }
}
