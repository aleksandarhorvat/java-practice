/*
Write a program that works with a list of toys. The file contains information about one toy in each line, in order of type (bunny, bear,...), 
color (blue, green...) and price with two decimal places.

1) Load data from a file whose name is entered from the keyboard. Print the data to the screen.
2) Create a class that represents one toy. Each toy is represented by its type, color and price.
3) Present the data about the toys with a new class that stores the data in an array of objects.
4) Enable the entry of a new toy into the list and save the changed data in a file.
5) Print data about toys with the entered color
6) Count how many toys are more expensive than the entered price
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class ThirdTask {

    static ListOfToys list = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the name of the file: ");
        String nameF = in.nextLine();
        System.out.println("Enter how many toys you want to be limit: ");
        int n = Integer.parseInt(in.nextLine());
        list = new ListOfToys(n);
        list.getToys(nameF);

        System.out.println("Instruments from file:");
        list.printInstruments();
        System.out.println();

        System.out.println("Enter type of the toy: ");
        String type = in.nextLine();
        System.out.println("Enter color of the color: ");
        String color = in.nextLine();
        System.out.println("Enter price of the price: ");
        double price = Double.parseDouble(in.nextLine());
        list.addToy(type, color, price);

        System.out.println("Enter color of the toys that you want to see: ");
        color = in.nextLine();
        list.printToysColor(color);

        System.out.println("Enter cost: ");
        price = Double.parseDouble(in.nextLine());
        int number = list.howMuchToysCostMore(price);
        System.out.println("Number of toys that have a higher price than entered: " + number);

        System.out.println();

        System.out.println("Enter name of file to export:");
        String nameF2 = in.nextLine();
        list.addToysToFile(nameF2);
        in.close();
    }
}
class Toy {
    String type, color;
    double price;

    public Toy(String type, String color, double price) {
        this.type = type;
        this.color = color;
        this.price = price;
    }

    public String toString() {
        return type + " " + color + " " + price;
    }
}
class ListOfToys {
    static int MAX_NUM_TOYS = 0;
    public Toy[] listToys;
    public int numToy;

    public ListOfToys(int n) {
        listToys = new Toy[n];
        MAX_NUM_TOYS = n;
        numToy = 0;
    }

    public void getToys(String f) {
        try {
            String type, color;
            double price = 0;
            File file = new File(f);
            Scanner reader = new Scanner(file);
            while (reader.hasNext() && numToy < MAX_NUM_TOYS) {
                type = reader.next();
                color = reader.next();
                price = Double.parseDouble(reader.next());
                addToy(type, color, price);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }

    public void addToy(String type, String color, double price) {
        if (numToy < MAX_NUM_TOYS) {
            listToys[numToy] = new Toy(type, color, price);
            numToy++;
        } else
            System.out.println("Too much instruments in array");
    }

    public void printInstruments() {
        for (int i = 0; i < numToy; i++) {
            System.out.print(listToys[i].type);
            System.out.print(" ");
            System.out.print(listToys[i].color);
            System.out.print(" ");
            System.out.println(listToys[i].price);
        }
    }

    public String toString() {
        String temp = "Toys [";
        if (numToy > 0) {
            temp += listToys[0];
        }
        for (int i = 1; i < numToy; i++) {
            temp += ", " + listToys[i];
        }
        temp += "]";
        return temp;
    }

    public void printToysColor(String color) {
        for (int i = 0; i < numToy; i++) {
            if (listToys[i].color.equals(color)) {
                System.out.println(listToys[i]);
            }
        }
    }

    public int howMuchToysCostMore(double price) {
        int number = 0;
        for (int i = 0; i < numToy; i++) {
            if (listToys[i].price > price) {
                number++;
            }
        }
        return number;
    }

    public void addToysToFile(String f) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < numToy; i++) {
                fw.write(listToys[i].type);
                fw.write(" ");
                fw.write(listToys[i].color);
                fw.write(" ");
                fw.write(listToys[i].price + "");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
