import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class StreamUtils {

    public static Stream<Integer> generateStreamWithPowersOfTwo(int n) {
        return IntStream
                .iterate(1, x -> x * 2)
                .limit(n)
                .boxed();
    }

}