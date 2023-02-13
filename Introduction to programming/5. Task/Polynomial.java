/*
Using the PolinomN module, write a program that
a) loads the polynomials p(x) and q(x) and prints their product,
b) prints the polynomial r(x) = q^2(x) - q(5) – 3p(x) - 2x^5 + 3x.

Write and test on the polynomial r(x) a procedure that:
c) increases each non-zero coefficient of the polynomial by 7,
d) print the monomial with the smallest coefficient.
*/
public class Polynomial {

    public static void incrementBySeven(Polinom p) {
        for (int i = 0; i <= p.st; i++)
            if (p.k[i] != 0.0)
                p.k[i] = p.k[i] + 7;
    }

    public static void minCoe(Polinom p) {
        Polinom temp = new Polinom();
        double minCoef, maxCoef;
        int minDegree, maxDegree;
        minCoef = p.k[p.st];
        minDegree = p.st;
        maxCoef = p.k[p.st];
        maxDegree = p.st;
        for (int i = p.st; i >= 0; i--) {
            if (p.k[i] != 0 && p.k[i] < minCoef) {
                minCoef = p.k[i];
                minDegree = i;
            }
        }
        temp.st = minDegree;
        temp.k[temp.st] = minCoef;
        System.out.println();
        PolinomN.stampaj(temp);
    }

    public static void main(String[] args) {
        System.out.println("Enter the polynomial p:");
        Polinom p = PolinomN.ucitaj();
        System.out.println();
        System.out.println("Enter the polynomial q:");
        Polinom q = PolinomN.ucitaj();
        System.out.println();
        Polinom pq = PolinomN.puta(p, q);
        if (pq != null) {
            System.out.print("p(x)q(x) = ");
            PolinomN.stampaj(pq);
        } else
            System.out.print("The polynomial p(x)q(x) is of too high degree.");
        System.out.println();

        Polinom pom1 = new Polinom();
        pom1.st = 5;
        pom1.k[5] = -2;
        pom1.k[1] = 3;


        Polinom qNa2 = PolinomN.puta(q, q);
        Polinom sab = PolinomN.brojPuta(p, -3.0);
        Polinom r = PolinomN.saberi(qNa2, sab);
        r = PolinomN.saberi(r, sab);
        r = PolinomN.oduzmi(r, pom1);
        r.k[0] = r.k[0] + 8 - PolinomN.izracunaj(5.0, q);
        System.out.println();
        System.out.print("r(x) = ");
        PolinomN.stampaj(r);
        System.out.println();

        System.out.println();
        System.out.print("The monomial with the smallest coefficient is: ");
        minCoe(r);
        System.out.println();

        System.out.println();
        System.out.print("r(x) after increasing each non-zero coefficient of the polynomial by 7, looks like this: ");
        incrementBySeven(r);
        PolinomN.stampaj(r);
        System.out.println();
    }
}
