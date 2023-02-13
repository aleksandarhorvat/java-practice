/*
Write a program that creates a file named file1.txt on the disk and writes in it:

The first row
Second line

Fourth row

Write a program that prints the contents of the file file1.txt on the screen.
	-Modify the program to list the file whose name is entered by the user.
Load all real numbers from the file numbers.txt and calculate the sum and average of the numbers.
The file names.txt contains common names.
Each name is on a separate line in the file and there are no duplicates. 
Write a program that checks whether the string entered by the user is in the file and prints the appropriate message.
If the string is not in the file, the program should add the string to the end of the file.
*/
import java.util.*;
import java.io.*;
public class TaskWithFiles {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of file that you want to print out: ");
        String file = in.nextLine();
        enter();
        Svetovid.out.println();
        read(file);
        numbers();
        System.out.println("Enter the name of name that you want to find: ");
        String name = in.nextLine();
        in.close();
        word(name);
    }

    static void enter() {
        try {
            FileWriter fw = new FileWriter("file1.txt");
            fw.write("First row" + System.lineSeparator());
            fw.write("Second row" + System.lineSeparator());
            fw.write(System.lineSeparator());
            fw.write("Forth row" + System.lineSeparator());
            System.out.println("\nEverything is printed");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    static void read(String f) {
        try {
            String line;
            File file = new File(f);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                line = in.nextLine();
                System.out.println(line);
            }
            System.out.println();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }

    static void numbers() {
        try {
            double sum = 0;
            int counter = 0;
            File file = new File("numbers.txt");
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                double number = Double.parseDouble(in.next());
                sum += number;
                counter++;
            }
            System.out.println("Sum of numbers is: " + sum);
            System.out.println("Avarage number is: " + sum / counter);
            System.out.println();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }

    static void word(String name) {
        boolean check = false;
        try {
            int counter = 0;
            String line;
            File file = new File("names.txt");
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                counter++;
                line = in.nextLine();
                if (line.equals(name)) {
                    System.out.println("There is already this name in file on line " + counter);
                    check = true;
                    break;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        if (!check) {
            try {
                FileWriter fw = new FileWriter("names.txt", true);
                fw.write(name + System.lineSeparator());
                fw.close();
                System.out.println("The name you entered doen't exist and its appended to end of file");
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }
}
