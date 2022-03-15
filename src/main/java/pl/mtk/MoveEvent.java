package pl.mtk;


public class MoveEvent implements GameEvent {

    private String origin;
    private String destination;

//    public MoveEvent(String origin, String destination) {
//        this.move = move;
//    }

//    private final Move move;

    @Override
    public boolean isPossible(Game game) {
        return true;
//        return game.possibleMoves.contains(move);
    }

    @Override
    public Game process(Game game) {
        return null;
    }
}
