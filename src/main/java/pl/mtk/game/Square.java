package pl.mtk.game;

import pl.mtk.game.pieces.Piece;

import java.util.Objects;

public class Square {

    public Square(Color color) {
        this.color = color;
    }

    public final Color color;
    private Piece piece = null;

    public void place(Piece piece) {
        this.piece = piece;
    }

    public Piece lift() throws Exception {
        if (Objects.isNull(this.piece)) {
            throw new IllegalStateException("Null piece cannot be lifted");
        } else {
            var liftedPiece = this.piece;
            this.piece = null;
            return liftedPiece;
        }
    }

}
