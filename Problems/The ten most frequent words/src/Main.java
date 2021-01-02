import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Arrays.stream(new Scanner(System.in).nextLine()
                .toLowerCase()
                .replaceAll("[^\\w\\s]", "")
                .split(" "))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted((stringListEntry, t1) ->
                        t1.getValue().size() - stringListEntry.getValue().size())
                .limit(10)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}