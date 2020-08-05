package pl.mtk.game.chessboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.mtk.game.Color;
import pl.mtk.game.pieces.Piece;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Map.entry;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Tile {
    @JsonIgnore
    private static final Map<Integer, String> numbersToColumn = Map.ofEntries(
            entry(0,"A"),
            entry(1,"B"),
            entry(2,"C"),
            entry(3,"D"),
            entry(4,"E"),
            entry(5,"F"),
            entry(6,"G"),
            entry(7,"H"));
    @JsonIgnore
    private Position position;
    @JsonIgnore
    private Color color;
    private String name;
    private Piece piece;
    private boolean selected;
    private boolean available;

    public static Tile createTile(Position position) {
        return Tile.builder()
                .position(position)
                .color(findColor(position))
                .name(findName(position))
                .selected(false)
                .available(false)
                .piece(null)
                .build();
    }

    private static Color findColor(Position position) {
        return (position.getColumn() + position.getRow() % 2 == 0) ?  Color.WHITE : Color.BLACK;
    }

    public static String findName(Position position) {
        return numbersToColumn.get(position.getColumn()) + (position.getRow() + 1);
    }

    public boolean containsPiece() { return piece != null; }

    public void clearPiece() {
        this.piece = null;
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class Position {

        int row, column;

    }

}

