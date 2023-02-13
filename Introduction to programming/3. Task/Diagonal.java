/*
Write a program that loads a natural number n (1 <= n <= 20) and a matrix of integers A in the format n*m.
Let m be the maximum element of the main diagonal of the matrix A. The program should print how many there are
of elements off the diagonal that are strictly smaller than it.

                        | 4-2 3 |
Example. For n = 3, A = | 2 3 4 |
                        |-7 5 4 |

The maximum element of the main diagonal is 4. The number of off-diagonal elements that are strictly less than 4: 4.
*/
import java.util.Scanner;
public class Diagonal {

    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Enter n such that (1<=n<=20): ");
            n = Integer.parseInt(in.nextLine());
        } while (n < 1 || n > 20);
        int[][] A = new int[n][n];
        int m = 0, counter = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                System.out.println("Enter matrix element [" + i + "][" + j + "]: ");
                A[i][j] = Integer.parseInt(in.nextLine());
                if (i == j)
                    if (m < A[i][j])
                        m = A[i][j];
            }
            in.close();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i != j)
                    if (m > A[i][j])
                        counter++;
            }
        System.out.println("The maximum element of the main diagonal is " + m + ". The number of off-diagonal elements that are strictly less than " + m + ": " + counter);
    }
}
