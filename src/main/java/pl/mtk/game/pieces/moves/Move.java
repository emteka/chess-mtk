package pl.mtk.game.pieces.moves;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Move {

    @Getter
    public enum Direction {
        UP(0, 1),
        UPRIGHT(1, 1),
        RIGHT(1, 0),
        DOWNRIGHT(1, -1),
        DOWN(0, -1),
        DOWNLEFT(-1, -1),
        LEFT(-1, 0),
        UPLEFT(-1, 1);

        int horizontal, vertical;

        Direction(int horizontal, int vertical) {
            this.horizontal = horizontal;
            this.vertical = vertical;
        }
    }

    int horizontal, vertical;
    private boolean attacking = true;
    private boolean friendly = true;

    public Move(int factor, Direction direction) {
        this.horizontal = direction.getHorizontal() * factor;
        this.vertical = direction.getVertical() * factor;
    }

    public Move(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    private  Move attacking() {
        this.friendly = false;
        return this;
    }

    private  Move friendly() {
        this.attacking = false;
        return this;
    }

    public static List<Move> getMoves( Direction direction) {
        return getMoves(8, direction);
    }

    public static List<Move> getMoves(int limit, Direction direction) {
        List<Move> moves = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            moves.add(new Move(i, direction));
        }
        return moves;
    }

    public static List<Move> getAttackingMoves(int limit, Direction direction) {
        List<Move> moves = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            moves.add(new Move(i, direction).attacking());
        }
        return moves;
    }

    public static List<Move> getFriendlyMoves(int limit, Direction direction) {
        List<Move> moves = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            moves.add(new Move(i, direction).friendly());
        }
        return moves;
    }

    public static List<List<Move>> getKnightsMoves() {
        List<List<Move>> moves = new ArrayList<>();
        moves.add(List.of(new Move(1, 2)));
        moves.add(List.of(new Move(1, -2)));
        moves.add(List.of(new Move(-1, 2)));
        moves.add(List.of(new Move(-1, -2)));
        moves.add(List.of(new Move(2, 1)));
        moves.add(List.of(new Move(-2, 1)));
        moves.add(List.of(new Move(2, -1)));
        moves.add(List.of(new Move(-2, -1)));
        return moves;
    }
}
