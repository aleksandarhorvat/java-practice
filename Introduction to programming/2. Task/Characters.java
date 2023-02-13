//Write a program that loads a sequence of characters until the character '.' and prints how many small letters, numbers,
//punctuation marks ('.', ',', ';' , ':') and other characters are among the entered characters.
import java.util.Scanner;
public class Characters {

    public static void main(String[] args) {
        int big = 0, small = 0, punctuation = 0, other = 0, numbers = 0;
        char a;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Enter a character: ");
            a = in.next().charAt(0);
            if (a >= 'A' && a <= 'Z')
                big++;
            else if (a >= 'a' && a <= 'z')
                small++;
            else if (a >= '0' && a <= '9')
                numbers++;
            else if (a == '.' || a == ',' || a == ';' || a == ':')
                punctuation++;
            else
                other++;
        } while (a != '.');
        in.close();
        punctuation--;
        System.out.println("There are " + big + " big ones, there are " + small + " small ones, punctuation there are " + punctuation + ", numbers there are " + numbers + ", and there are " + other + " others");
    }
}
