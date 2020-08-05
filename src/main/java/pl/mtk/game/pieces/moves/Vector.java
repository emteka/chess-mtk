package pl.mtk.game.pieces.moves;

import lombok.Getter;
import pl.mtk.game.chessboard.Tile;

@Getter
public class Vector {

    public enum Direction {
        UP(0, 1),
        UPRIGHT(1, 1),
        RIGHT(1, 0),
        DOWNRIGHT(1, -1),
        DOWN(0, -1),
        DOWNLEFT(-1, -1),
        LEFT(-1, 0),
        UPLEFT(-1, 1);

        private final int horizontal, vertical;

        Direction(int horizontal, int vertical) {
            this.horizontal = horizontal;
            this.vertical = vertical;
        }
    }

    int horizontal, vertical;

    public Vector(Direction direction) {
        this.horizontal = direction.horizontal;
        this.vertical = direction.vertical;
    }

    public Vector(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Vector scale(int factor) {
        this.horizontal *= factor;
        this.vertical *= factor;
        return this;
    }

    public Tile.Position toDestination(Tile.Position origin) {
        int horizontal = origin.getColumn() + this.getHorizontal();
        int vertical = origin.getRow() + this.getVertical();
        return new Tile.Position(vertical, horizontal);
    }

}

