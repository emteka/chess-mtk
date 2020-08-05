package pl.mtk.game.pieces.moves;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Testt {
    public static void main(String[] args) {
        List<String> a = List.of("b", "a", "b", "a", "a", "a", "a");
        Predicate<String> a1 = l -> l.equals("a");
        Stream.concat(a
                .stream()
                .takeWhile(a1)
                ,
                a.stream().dropWhile(a1).limit(1)).forEach(System.out::println);
    }
}
