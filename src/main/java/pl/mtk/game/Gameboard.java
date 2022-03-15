package pl.mtk.game;

import java.util.function.Consumer;

public class Gameboard {

    public Gameboard(Square[][] squares) {
        this.squares = squares;
    }

    Square[][] squares;

    public Square position(Position position) {
        return this.squares[position.file][position.rank];
    }
}
