import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<List<Integer>> matrix = new ArrayList<>();

        String row = scanner.nextLine();
        while (!"end".equals(row)) {
            matrix.add(Arrays.stream(row.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
            row = scanner.nextLine();
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                int up = i == 0 ? matrix.size() - 1 : i - 1;
                int down = i == matrix.size() - 1 ? 0 : i + 1;
                int left = j == 0 ? matrix.get(0).size() - 1 : j - 1;
                int right = j == matrix.get(0).size() - 1 ? 0 : j + 1;

                System.out.print((matrix.get(up).get(j) + matrix.get(down).get(j) +
                        matrix.get(i).get(left) + matrix.get(i).get(right)) + " ");
            }
            System.out.println();
        }
    }
}