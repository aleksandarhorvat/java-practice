//Write a program that determines whether the loaded natural number is prime (use a boolean variable).
import java.util.Scanner;
public class PrimeNumber {

    public static void main(String[] args) {
        boolean state = true;
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number: ");
        n = Integer.parseInt(in.nextLine());
        in.close();
        if (n <= 1)
            state = false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                state = false;
        if (state == true)
            System.out.println("The entered number is prime");
        else
            System.out.println("The entered number is not prime");
    }
}
