/*
a) Loads String data from the file into an instance of the ArrayList class.
b) finds the last position where the entered element is in the list and prints the next one (if it exists). if the element is the last delete it
c) prints the resulting list in a new file
-------------------------------------------------- -------------------------------------------------- -------------------------------------
Using the given Stack class, write a method that:
a) from the file with the given name, it loads double elements into an instance of the Stack class,
b) removes available elements from the Stack parameter while they are negative and if there are more elements,
c) prints all elements in the Stack structure to the screen

The main program should demonstrate the use of these methods.
*/
import java.io.*;
import java.util.*;
public class FirstTask {

    private static Stack <Double> loadNumbers(String f) {
        try {
            File file = new File(f);
            Scanner in = new Scanner(file);
            Stack <Double> stack = new Stack <Double>();
            while (in.hasNext()) {
                stack.push(Double.parseDouble(in.next()));
            }
            in.close();
            return stack;
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList <String> list = new ArrayList <String>();
        String pathIn = "names.txt";
        try {
            File file = new File(pathIn);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                list.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        System.out.println(list);
        System.out.println();
        System.out.println("Enter your element: ");
        Scanner scan = new Scanner(System.in);
        String element = scan.nextLine();
        int position = list.lastIndexOf(element);
        if (position == (list.size() - 1))
            list.remove(list.get(position));
        else
            System.out.println("Next element is: " + list.get(position + 1));

        System.out.println("\nEnter the name of the file to export: ");
        String export = scan.nextLine();
        export += ".txt";
        scan.close();
        try {
            FileWriter fw = new FileWriter(export);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(list);
        System.out.println("--------------------------------------------------");

        Stack <Double> stackNumbers = new Stack <Double>();
        stackNumbers = loadNumbers("numbers.txt");


        if (!stackNumbers.empty()) {
            while (!stackNumbers.empty()) {
                if (stackNumbers.peek() < 0)
                    stackNumbers.pop();
                else
                    break;
            }
        }

        System.out.println();
        System.out.println(stackNumbers);
    }
}