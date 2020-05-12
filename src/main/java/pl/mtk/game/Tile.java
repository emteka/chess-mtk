package pl.mtk.game;

import lombok.Getter;
import lombok.Setter;
import pl.mtk.game.pieces.Piece;

import java.util.Map;

import static java.util.Map.entry;

@Getter
@Setter
public class Tile {

    public static final Map<Integer, String> numbersToColumn = Map.ofEntries(
            entry(0,"A"),
            entry(1,"B"),
            entry(2,"C"),
            entry(3,"D"),
            entry(4,"E"),
            entry(5,"F"),
            entry(6,"G"),
            entry(7,"H"));

    public static final Map<Character, Integer> columnToNumbers = Map.ofEntries(
            entry('A', 0),
            entry('B', 1),
            entry('C', 2),
            entry('D', 3),
            entry('E', 4),
            entry('F', 5),
            entry('G', 6),
            entry('H', 7));

    private Color color;
    private Piece piece;
    private String name;
    private boolean selected;
    private boolean available;

    private Tile(Color color) {
        this.color = color;
    }

    private enum Color {
        WHITE, BLACK
    }

    public static Tile whiteTile() {
        return new Tile(Color.WHITE);
    }

    public static Tile blackTile() {
        return new Tile(Color.BLACK);
    }

    public Tile setName(int row, int column) {
        this.name = numbersToColumn.get(column);
        this.name += String.valueOf(row + 1);
        return this;
    }

    public boolean containsPiece() { return piece != null; }

    @Override
    public String toString() {
        if (piece != null) {
            return piece.toString();
        }
        return color.toString();
    }

    public Position getPosition() {
        return new Position(column(), row());
    }

    private int column() {
        return columnToNumbers.get(this.getName().charAt(0));
    }

    private int row() {
        return this.getName().charAt(1) - '1';
    }

    @Getter
    public class Position {
        int column, row;

        public Position(int column, int row) {
            this.column = column;
            this.row = row;
        }
    }
}

