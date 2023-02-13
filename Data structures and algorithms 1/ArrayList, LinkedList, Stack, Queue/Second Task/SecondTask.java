/*
Write a program that creates an ArrayList of strings and inserts some names into it (at least 4).
Print the list and print it after each subsequent change.

Add another name to place 2.
Change the name in position 4 and write what was in that position.
Print on the screen elements in even positions.
Write the elements that start with the letter "S".
Remove element from position 3.
Throw out an element that is equal to the input.
Load the names (Strings, each in a new line) from the file and insert into LinkedList<String>.
Add all the names from the ArrayList in the first task. Print all names in a new file.
*/
import java.util.*;
import java.io.*;
public class SecondTask {

    public static void main(String[] args) {
        ArrayList <String> list = new ArrayList <String>(Arrays.asList("aleksandar", "stefan", "bogdan", "nikolina"));
        System.out.println(list);
        list.add(2, "2NAME");
        System.out.println(list);
        System.out.println(list.get(4));
        list.set(4, "4NAME");
        System.out.println(list);
        for (int i = 1; i < list.size(); i += 2) {
            System.out.println(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(0) == 'S' || list.get(i).charAt(0) == 's')
                System.out.println(list.get(i));
        }
        list.remove(3);
        System.out.println(list);
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name that you want to remove from list: ");
        String ime = in.nextLine();
        list.remove(ime);
        System.out.println(list);

        List <String> list2 = new LinkedList <String>();
        list2.addAll(list);
        try {
            String pathIn = "names.txt";
            File file = new File(pathIn);
            in = new Scanner(file);
            while (in.hasNext()) {
                list2.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
        try {
            String pathOut = "newNames.txt";
            FileWriter fw = new FileWriter(pathOut);
            for (int i = 0; i < list2.size(); i++) {
                fw.write(list2.get(i));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(list2);
    }
}