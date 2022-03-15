package pl.mtk;

public interface GameEvent {
    Game process(Game game);
    boolean isPossible(Game game);
}
