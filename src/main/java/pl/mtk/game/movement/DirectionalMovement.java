package pl.mtk.game.movement;

import pl.mtk.game.MoveDirection;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectionalMovement implements Movement {

    private DirectionalMovement(int range, MoveDirection moveDirection) {
        this.range = range;
        this.moveDirection = moveDirection;
    }

    public static Set<Movement> directionalMovement(int range, Set<MoveDirection> moveDirections) {
        return moveDirections.stream()
                .map(direction -> new DirectionalMovement(range, direction))
                .collect(Collectors.toSet());
    }

    public static Set<Movement> directionalMovement(int range, MoveDirection... moveDirections) {
        return directionalMovement(range, Arrays.stream(moveDirections)
                .collect(Collectors.toSet()));
    }

    public final int range;
    public final MoveDirection moveDirection;

}
