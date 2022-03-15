package pl.mtk;

import pl.mtk.game.rules.Rules;

public class GameEngine {

    public static Game createGame(Rules rules) {
        return new Game(rules);
    }

    public static Game process(GameEvent gameEvent, Game game) {
        return gameEvent.isPossible(game) ? gameEvent.process(game) : game;
    }

}
