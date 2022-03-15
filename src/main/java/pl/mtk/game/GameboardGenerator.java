package pl.mtk.game;

import pl.mtk.game.rules.Rules;

public class GameboardGenerator {

    private GameboardGenerator() {
    }

    private int size;

    public static GameboardGenerator newGameboard() {
        return new GameboardGenerator();
    }

    public GameboardGenerator ofSize(int size) {
        this.size = size;
        return this;
    }

    public Gameboard generate() {
        Square[][] squares = new Square[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                squares[i][j] = new Square(i + j % 2 == 0 ? Color.BLACK : Color.WHITE);
            }
        }

        return new Gameboard(squares);
    }

}
