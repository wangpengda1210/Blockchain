import java.util.Scanner;
import java.util.stream.*;

class PrimeNumbers {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    private static boolean isPrime(long number) {
        // write your code here
        if (number <= 1) {
            return false;
        }
        LongStream stream = LongStream.rangeClosed(1, number);
        return stream.filter(it -> number % it == 0).count() == 2;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine().trim();

        int n = Integer.parseInt(line);

        System.out.println(isPrime(n) ? "True" : "False");
    }
}