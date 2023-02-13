//Write a program that determines all the divisors of the loaded natural number, if there are none, then write that it is a prime number (use a for loop).
import java.util.Scanner;
public class Divider {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        do {
            System.out.println("Enter a positive number: ");
            n = Integer.parseInt(in.nextLine());
        } while (n <= 0);
        in.close();
        int temp = 0;
        for (int i = 2; i <= (n / 2); i++) {
            if (n % i == 0) {
                System.out.println("The number is divisible by: " + i);
                temp++;
            }
        }
        if (n == 1) {
            System.out.println("You entered the number one");
        } else if (temp == 0) {
            System.out.println("The number is prime");
        }
    }
}
