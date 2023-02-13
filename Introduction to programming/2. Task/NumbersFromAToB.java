//Write a program that loads integers a and b, and then a sequence of numbers from the interval [a, b]
//(the input is interrupted when a number outside the interval is entered) and prints how many numbers were entered and the smallest loaded number.
import java.util.Scanner;
public class NumbersFromAToB {

    public static void main(String[] args) {
        int a, b, n, i = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the limit a: ");
        a = Integer.parseInt(in.nextLine());
        do {
            System.out.println("Enter the limit b such that it is greater than a: ");
            b = Integer.parseInt(in.nextLine());
        } while (b < a);
        int min = b + 1;
        do {
            System.out.println("Enter a number n such that it is in the range from " + a + " to " + b + ": ");
            n = Integer.parseInt(in.nextLine());
            if ((n >= a && n <= b) && min > n) {
                min = n;
                i++;
            }
        } while (n >= a && n <= b);
        in.close();
        System.out.println("You have entered " + i + " numbers, and the smallest of them is " + min);
    }
}
