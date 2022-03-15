package pl.mtk.game.movement;

import pl.mtk.game.MoveShape;
import pl.mtk.game.Position;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.mtk.game.Position.position;

public class LeapMovement implements Movement {

    public final Position destination;

    private LeapMovement(Position destination) {
        this.destination = destination;
    }

    public static Set<Movement> leapMovement(MoveShape moveShape) {
        return moveShape.shape.stream()
                .map(shape -> new LeapMovement(position(shape.file, shape.rank)))
                .collect(Collectors.toSet());
    }


}
