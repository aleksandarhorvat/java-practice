/*
For the entered n and r (0 <= n <= 25, and r, 5 <= r <= 20), calculate the value of the integer sequence f_n:
	(a) recursively by definition,
	(b) recursively using the accumulating parameter,
	(c) iteratively
an array is defined as follows:
	f_n = |f_n-r - g_n-1| , n >= r, n divisible by 5
	f_n = -g_n-3 + f_n-1 , n >= r, n is not divisible by 5
	f_n = 3*r-2, n < r

	g_n = g_n-r + f_n-2*g_n-r+1 , n >= r, n + r divisible by 6
	g_n = f_n-r + g_n-2*f_n-r+1 , n >= r, n + r when dividing by 6 gives a remainder of 1
	g_n = max(f_n-r+2, g_n-r+1) , n >= r, otherwise
	g_n = (-1)^n - n , n < r

Example: for n = 7 and r = 5 -> f_n = 23, for n = 25 and r = 10 -> f_n = 451, for n = 17 and r = 5 -> f_n = 3374
*/
import java.lang.Math;
import java.util.Scanner;
public class Recursion {

    public static int fDef(int n, int r) {
        if (n < r)
            return 3 * r - 2;
        else if (n % 5 == 0)
            return Math.abs(fDef(n - r, r) - gDef(n - 1, r));
        else
            return -gDef(n - 3, r) + fDef(n - 1, r);
    }

    public static int gDef(int n, int r) {
        if (n < r)
            return (int) Math.pow(-1, n) - n;
        else if ((n + r) % 6 == 0)
            return gDef(n - r, r) + fDef(n - 2, r) * gDef(n - r + 1, r);
        else if ((n + r) % 6 == 1)
            return fDef(n - r, r) + gDef(n - 2, r) * fDef(n - r + 1, r);
        else
            return Math.max(fDef(n - r + 2, r), gDef(n - r + 1, r));
    }

    public static int fg(int[] f, int[] g, int i, int n, int r) {
        if (i > n)
            return f[r];
        else {
            if (i % 5 == 0)
                f[r] = Math.abs(f[0] - g[r - 1]);
            else
                f[r] = -g[r - 3] + f[r - 1];
            if ((i + r) % 6 == 0)
                g[r] = g[0] + f[r - 2] * g[1];
            else if ((i + r) % 6 == 1)
                g[r] = f[0] + g[r - 2] * f[1];
            else
                g[r] = Math.max(f[2], g[1]);
            for (int j = 0; j < r; j++) {
                f[j] = f[j + 1];
                g[j] = g[j + 1];
            }
        }
        return fg(f, g, i + 1, n, r);
    }

    public static int fRek(int n, int r) {
        int[] f = new int[r + 1];
        int[] g = new int[r + 1];
        if (n < r)
            return 3 * r - 2;
        else {
            for (int i = 0; i < r; i++) {
                f[i] = 3 * r - 2;
                g[i] = (int) Math.pow(-1, i) - i;
            }
            return fg(f, g, r, n, r);
        }
    }

    public static int fIter(int n, int r) {
        if (n < r)
            return 3 * r - 2;
        int[] f = new int[r + 1];
        int[] g = new int[r + 1];
        for (int i = 0; i < r; i++) {
            f[i] = 3 * r - 2;
            g[i] = (int) Math.pow(-1, i) - i;
        }
        for (int i = r; i <= n; i++) {
            if (i % 5 == 0)
                f[r] = Math.abs(f[0] - g[r - 1]);
            else
                f[r] = -g[r - 3] + f[r - 1];
            if ((i + r) % 6 == 0)
                g[r] = g[0] + f[r - 2] * g[1];
            else if ((i + r) % 6 == 1)
                g[r] = f[0] + g[r - 2] * f[1];
            else
                g[r] = Math.max(f[2], g[1]);
            for (int j = 0; j < r; j++) {
                f[j] = f[j + 1];
                g[j] = g[j + 1];

            }
        }
        return f[r];
    }

    static final int nMin = 0, nMax = 25, rMin = 5, rMax = 20;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, r;
        do {
            System.out.print("Enter n such that it is less than or equal to " + nMax + " and greater than or equal to " + nMin + ": ");
            n = Integer.parseInt( in .nextLine());
        } while (nMin > n && nMax < n);

        do {
            System.out.print("Enter n such that it is less than or equal to " + rMax + " and greater than or equal to " + rMin + ": ");
            r = Integer.parseInt( in .nextLine());
        } while (rMin > r && rMax < r);
		
		in.close();
		
        System.out.println("By definition: " + fDef(n, r));
        System.out.println("By accumulating parameters: " + fRek(n, r));
        System.out.println("By iteration: " + fIter(n, r));
    }
}
