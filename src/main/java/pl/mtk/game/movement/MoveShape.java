package pl.mtk.game;

import java.util.Set;

import static pl.mtk.game.Position.position;

public enum MoveShape {
    L_SHAPE(
            Set.of(
                    position(1, 2),
                    position(1, -2),
                    position(-1, 2),
                    position(-1, -2),
                    position(2, 1),
                    position(2, -1),
                    position(-2, 1),
                    position(-2, -1)
            )
    );

    MoveShape(Set<Position> shape) {
        this.shape = shape;
    }

    public final Set<Position> shape;

}
