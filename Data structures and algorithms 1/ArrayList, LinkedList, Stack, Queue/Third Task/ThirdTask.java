/*
Write a program that
(a) loads the strings from the file “r1.txt” into the string serving queue
(b) removes strings shorter than 6 characters from the beginning of the service queue
(c) print the content of the resulting line to a file specified by the user

Write a program that
(a) loads the integers from the files “p1.txt” and “p2.txt” onto two different integer stacks
(b) removes all numbers whose last digit is 6 from the top of the first stack
(c) removes from the top of the second stack all numbers greater than its successor
(d) merges data from two stacks into a new one, alternately inserting data (note that the stacks do not have to be of the same length)
(e) writes the content of the thus obtained stack to the file "pp.txt"
*/
import java.io.*;
import java.util.*;
public class ThirdTask {

    private static Queue <String> loadStrings(String f) {
        try {
            File file = new File(f);
            Scanner in = new Scanner(file);
            Queue <String> queue = new LinkedList <String>();
            while ( in .hasNext()) {
                queue.add(in.nextLine());
            }
            in.close();
            return queue;
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.println("There is no space in queue");
        }
        return null;
    }

    private static Stack <Integer> loadNumbers(String f) {
        try {
            File file = new File(f);
            Scanner in = new Scanner(file);
            Stack <Integer> stack = new Stack <Integer>();
            while (in.hasNext()) {
                stack.push(Integer.parseInt(in.next()));
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
        Queue <String> queueStrings = new LinkedList <String>();
        queueStrings = loadStrings("r1.txt");

        System.out.println(queueStrings);

        if (queueStrings.peek() != null) {
            while (queueStrings.peek() != null) {
                if (queueStrings.element().length() % 2 == 0) {
                    queueStrings.remove();
                } else {
                    break;
                }
            }
        }

        System.out.println(queueStrings);

        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of the file: ");
            String output = in.nextLine();
            output += ".txt";
            FileWriter fw = new FileWriter(output);
            if (queueStrings.peek() != null) {
                while (queueStrings.peek() != null) {
                    fw.write(queueStrings.remove());
                    fw.write(System.lineSeparator());
                }
            }
            in.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        Stack <Integer> firstStackNumbers = new Stack <Integer>();
        firstStackNumbers = loadNumbers("p1.txt");
        Stack <Integer> secondStackNumbers = new Stack <Integer>();
        secondStackNumbers = loadNumbers("p2.txt");

        System.out.println();
        System.out.println(firstStackNumbers);

        if (!firstStackNumbers.empty()) {
            while (!firstStackNumbers.empty()) {
                if (firstStackNumbers.peek() % 10 == 6)
                    firstStackNumbers.pop();
                else
                    break;
            }
        }

        System.out.println(firstStackNumbers);

        System.out.println();
        System.out.println(secondStackNumbers);

        if (!secondStackNumbers.empty()) {
            while (!secondStackNumbers.empty()) {
                int pom = secondStackNumbers.pop();
                if (pom <= secondStackNumbers.peek()) {
                    secondStackNumbers.push(pom);
                    break;
                }
            }
        }

        System.out.println(secondStackNumbers);

        System.out.println();
        System.out.println(firstStackNumbers);
        System.out.println(secondStackNumbers);

        Stack <Integer> bothStacks = new Stack <Integer>();
        if (!firstStackNumbers.empty() && !secondStackNumbers.empty()) {
            while ((!firstStackNumbers.empty() || !secondStackNumbers.empty())) {
                try {
                    bothStacks.push(firstStackNumbers.pop());
                } catch (IllegalStateException | EmptyStackException e) {
                    continue;
                }
                try {
                    bothStacks.push(secondStackNumbers.pop());
                } catch (IllegalStateException | EmptyStackException e) {
                    continue;
                }
            }
        }

        System.out.println();
        System.out.println(bothStacks);

        try {
            String out = "pp.txt";
            FileWriter fw = new FileWriter(out);
            if (!bothStacks.empty()) {
                while (!bothStacks.empty()) {
                    fw.write(bothStacks.pop().toString());
                    fw.write(" ");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}