/*
A colloquium is represented by the class Colloquium, which contains the following fields: month of holding, ordinal (first, second,...) and number of questions.
The course is represented by the Course class and contains a maximum of 10 colloquiums.
The class also defines a constructor and a toString() method.
In addition, the class defines the following methods:

1) A method that loads colloquium data from an arbitrary file (this functionality can also be implemented in the constructor).
2) A method that adds a new colloquium to the course.
3) A method that checks the average number of questions at colloquiums held in a certain month (the month is entered by the user and is a parameter of the method).
4) A method that records colloquium data in an arbitrary file.

Write a program that creates a course based on the input file (one line contains data about one colloquium, i.e. month, ordinal and number of questions separated by spaces),
and calls all the methods defined in the Course class in the order given above.
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class SecondTask {

    static Course list = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the name of the file: ");
        String nameF = in .nextLine();
        list = new Course();
        list.getColloquiums(nameF);

        System.out.println("Instruments from file:");
        list.printColloquiums();
        System.out.println();

        System.out.println("Enter the month in which the colloquium will be held: ");
        String month = in.nextLine();
        System.out.println("Enter the what colloquium is in order: ");
        String ordinal = in.nextLine();
        System.out.println("Enter how many questions the colloquium will have: ");
        int numberOfQuestions = Integer.parseInt(in.nextLine());
        list.addColloquium(month, ordinal, numberOfQuestions);

        System.out.println("Enter the month to see how many questions there are on average at colloquiums held in that month: ");
        month = in.nextLine();
        double answ = list.numberOfQuestionsInMonth(month);

        System.out.println("On average at colloquiums held in that month there is " + answ + " many");

        System.out.println();

        System.out.println("Enter name of file to export:");
        String nameF2 = in.nextLine();
        list.addColloquiumsToFile(nameF2);
        in.close();
    }
}
class Colloquium {
    String month, ordinal;
    int numberOfQuestions;

    public Colloquium(String month, String ordinal, int numberOfQuestions) {
        this.month = month;
        this.ordinal = ordinal;
        this.numberOfQuestions = numberOfQuestions;
    }

    public String toString() {
        return month + " " + ordinal + " " + numberOfQuestions;
    }
}
class Course {
    final static int MAX_NUM_COLLOQUIUM = 10;
    public Colloquium[] listColloquiums;
    public int numCol;

    public Course() {
        listColloquiums = new Colloquium[MAX_NUM_COLLOQUIUM];
        numCol = 0;
    }

    public String toString() {
        String temp = "Colloquiums [";
        if (numCol > 0) {
            temp += listColloquiums[0];
        }
        for (int i = 1; i < numCol; i++) {
            temp += ", " + listColloquiums[i];
        }
        temp += "]";
        return temp;
    }

    public void getColloquiums(String f) {
        try {
            String month, ordinal;
            int numberOfQuestions;
            File file = new File(f);
            Scanner reader = new Scanner(file);
            while (reader.hasNext() && numCol < MAX_NUM_COLLOQUIUM) {
                month = reader.next();
                ordinal = reader.next();
                numberOfQuestions = Integer.parseInt(reader.next());
                addColloquium(month, ordinal, numberOfQuestions);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }

    public void addColloquium(String month, String ordinal, int numberOfQuestions) {
        if (numCol < MAX_NUM_COLLOQUIUM) {
            listColloquiums[numCol] = new Colloquium(month, ordinal, numberOfQuestions);
            numCol++;
        } else
            System.out.println("Too much instruments in array");
    }

    public double numberOfQuestionsInMonth(String temp) {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < numCol; i++) {
            if (temp.equals(listColloquiums[i].month)) {
                counter++;
                sum += listColloquiums[i].numberOfQuestions;
            }
        }
        return sum / counter;
    }

    public void printColloquiums() {
        for (int i = 0; i < numCol; i++) {
            System.out.print(listColloquiums[i].month);
            System.out.print(" ");
            System.out.print(listColloquiums[i].ordinal);
            System.out.print(" ");
            System.out.println(listColloquiums[i].numberOfQuestions);
        }
    }

    public void addColloquiumsToFile(String f) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < numCol; i++) {
                fw.write(listColloquiums[i].month);
                fw.write(" ");
                fw.write(listColloquiums[i].ordinal);
                fw.write(" ");
                fw.write(listColloquiums[i].numberOfQuestions + "");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
