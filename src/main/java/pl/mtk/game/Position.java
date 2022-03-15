package pl.mtk.game;

public class Position {

    public final int rank;
    public final int file;

    private Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position position(String position) {
        int rank = position.charAt(1) - 49;
        int file = position.toLowerCase().charAt(0) - 97;
        return new Position(rank, file);
    }

    public static Position position(int rank, int file) {
        return new Position(rank, file);
    }
    public Position translate(int rank, int file) {
        return new Position(this.rank + rank, this.file + file);
    }

}
