package pl.mtk.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import pl.mtk.game.GameState;

import java.util.Map;


@Controller
public class GameController {

    @MessageMapping("/newgame")
    @SendTo("/topic/board")
    public GameState newGame(NewGameRequest newGameRequest, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionId();
        GameState gameState = GameState.startNewGame();
        headerAccessor.getSessionAttributes().put("gamestate", gameState);
        System.out.println("new game created" + sessionId);
        return gameState;
    }

    @MessageMapping("/move")
    @SendTo("/topic/board")
    public GameState move(SelectedTile selectedTile, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        Map<String, Object> attributesMap = headerAccessor.getSessionAttributes();
        String sessionId = headerAccessor.getSessionId();
        if (attributesMap.containsKey("gamestate")) {
            GameState gameState = (GameState) attributesMap.get("gamestate");
            gameState.analyze(selectedTile);
            System.out.println(sessionId);
            return gameState;
        }
        return null;
    }
}