import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<>();

        int number = scanner.nextInt();

        while (number != 0) {
            numbers.add(number);
            number = scanner.nextInt();
        }

        if (numbers.size() == 1) {
            System.out.println(true);
            return;
        }

        int last = numbers.get(0);
        boolean isOrdered = true;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < last) {
                isOrdered = false;
                break;
            }
            last = numbers.get(i);
        }

        if (isOrdered) {
            System.out.println(true);
        } else {
            last = numbers.get(0);
            isOrdered = true;
            for (int i = 1; i < numbers.size(); i++) {
                if (numbers.get(i) > last) {
                    isOrdered = false;
                    break;
                }
                last = numbers.get(i);
            }
            System.out.println(isOrdered);
        }

    }
}