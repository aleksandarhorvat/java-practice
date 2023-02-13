//Load data from a file whose name is entered from the keyboard. Print the data to the screen.
//Create a class that represents a single instrument. Each instrument is represented by its type, manufacturer, code and price.
//Represent instrument data with a new class that stores the data in an array of objects.
//Enable the entry of a new instrument in the list and record the data changed in this way in a file.
//Print data on instruments whose code is of even length.
//Count how many instruments there are of the entered type.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class FirstTask {

    static ListOfInstruments list = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the name of the file: ");
        String nameF = in.nextLine();
        list = new ListOfInstruments();
        list.getInstruments(nameF);

        System.out.println("Instruments from file:");
        list.printInstruments();
        System.out.println();

        System.out.println("Enter type of the instrument: ");
        String type = in.nextLine();
        System.out.println("Enter manufacturer of the instrument: ");
        String manufacturer = in.nextLine();
        System.out.println("Enter code of the instrument: ");
        String code = in.nextLine();
        System.out.println("Enter price of the instrument: ");
        double price = Double.parseDouble(in.nextLine());
        list.addInstrument(type, manufacturer, code, price);

        list.printEvenInstruments();

        System.out.println("Enter type of the instrument that you want to see: ");
        type = in.nextLine();
        int number = list.howMuchInstrumentsType(type);

        System.out.println("How much of that type is there: " + number);

        System.out.println();

        System.out.println("Enter name of file to export:");
        String nameF2 = in.nextLine();
        list.addInstrumentsToFile(nameF2);
        in.close();
    }
}
class Instrument {
    String type, manufacturer, code;
    double price;

    public Instrument(String type, String manufacturer, String code, double price) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.code = code;
        this.price = price;
    }

    public String toString() {
        return type + " " + manufacturer + " " + code + " " + price;
    }
}
class ListOfInstruments {
    final static int MAX_NUM_INSTRUMENTS = 100;
    public Instrument[] listInstruments;
    public int numIn;

    public ListOfInstruments() {
        listInstruments = new Instrument[MAX_NUM_INSTRUMENTS];
        numIn = 0;
    }

    public void getInstruments(String f) {
        try {
            String type, manufacturer, code;
            double price = 0;
            File file = new File(f);
            Scanner reader = new Scanner(file);
            while (reader.hasNext() && numIn < MAX_NUM_INSTRUMENTS) {
                type = reader.next();
                manufacturer = reader.next();
                code = reader.next();
                price = Double.parseDouble(reader.next());
                addInstrument(type, manufacturer, code, price);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            e.printStackTrace();
        }
    }

    public void addInstrument(String type, String manufacturer, String code, double price) {
        if (numIn < MAX_NUM_INSTRUMENTS) {
            listInstruments[numIn] = new Instrument(type, manufacturer, code, price);
            numIn++;
        } else
            System.out.println("Too much instruments in array");
    }

    public void printInstruments() {
        for (int i = 0; i < numIn; i++) {
            System.out.print(listInstruments[i].type);
            System.out.print(" ");
            System.out.print(listInstruments[i].manufacturer);
            System.out.print(" ");
            System.out.println(listInstruments[i].code);
            System.out.print(" ");
            System.out.println(listInstruments[i].price);
        }
    }

    public void printEvenInstruments() {
        for (int i = 0; i < numIn; i++) {
            if (listInstruments[i].code.length() % 2 == 0) {
                System.out.println(listInstruments[i]);
            }
        }
    }

    public String toString() {
        String temp = "Instruments [";
        if (numIn > 0) {
            temp += listInstruments[0];
        }
        for (int i = 1; i < numIn; i++) {
            temp += ", " + listInstruments[i];
        }
        temp += "]";
        return temp;
    }


    public int howMuchInstrumentsType(String type) {
        int number = 0;
        for (int i = 0; i < numIn; i++) {
            if (listInstruments[i].type.equals(type)) {
                number++;
            }
        }
        return number;
    }

    public void addInstrumentsToFile(String f) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < numIn; i++) {
                fw.write(listInstruments[i].type);
                fw.write(" ");
                fw.write(listInstruments[i].manufacturer);
                fw.write(" ");
                fw.write(listInstruments[i].code);
                fw.write(" ");
                fw.write(listInstruments[i].price + "");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
