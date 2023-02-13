//Write a program that determines how many prime digits the loaded natural number has (use a while loop).
import java.util.Scanner;
public class PrimeDigits {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, temp = 0;
        System.out.println("Enter the number: ");
        n = Integer.parseInt(in.nextLine());
        in.close();
        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            if (digit == 2 || digit == 3 || digit == 5 || digit == 7)
                temp++;
        }
        System.out.println("The number of prime digits in the entered number is: " + temp);
    }
}
