/*
Write a class Market which represents a list of stalls in a market.
Each stall has an identification number, the name of the hall in which it is located (String type) and a list of groceries.
The list of stalls is sorted by number. Each food item has a name and a unique code.

Create and test methods that:
1) Adds a new stall with the given identification number and hall name.
2) Adds a grocery item to the counter with the given number. If the stall with the given number does not exist, first add the stall with that number, and then put the food on it.
3) Prints the numbers of stalls that have more than N items.
4) Merges the last two stalls in the given hall into one by moving the groceries from the last to the penultimate stall and deleting the last stall.
   If there are not at least 2 stalls in a given hall, the merger is cancelled.
   Don't take up new storage for groceries.
*/

import java.util.*;

class Market {

    private Stall firstStall;

    class Stall {
        int ID;
        String nameOfHall;
        Stall relation;
        Groceries content;

        public Stall(int ID, String nameOfHall) {
            this.ID = ID;
            this.nameOfHall = nameOfHall;
            this.relation = null;
        }

        public String toString() {
            String result = "\n[ID: " + ID + ", Name of the hall: " + nameOfHall;
            Groceries current = content;
            while (current != null) {
                result += " " + current;
                current = current.relation;
            }

            result += "]\n---------------------------------------------------------";
            return result;
        }
    }

    class Groceries {
        String name;
        int code;
        Groceries relation;

        public Groceries(String name, int code) {
            this.name = name;
            this.code = code;
            this.relation = null;
        }

        public String toString() {
            return "\n[Name: " + name + ", Code: " + code + "]";
        }
    }

    public String toString() {
        String result = "Stall:\n---------------------------------------------------------";
        Stall current = firstStall;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }

    //1) Adds a new stall with the given identification number and hall name.
    public void addStall(int ID, String nameOfHall) {
        if (!existStall(ID)) {
            Stall temp = new Stall(ID, nameOfHall);
            if (firstStall == null || firstStall.ID > ID) {
                temp.relation = firstStall;
                firstStall = temp;
            } else {
                Stall previous = firstStall;
                while (previous.relation != null && previous.relation.ID < ID) {
                    previous = previous.relation;
                }
                if (previous.relation == null || previous.relation.ID > ID) {
                    temp.relation = previous.relation;
                    previous.relation = temp;
                }
            }
        }
    }

    public boolean existStall(int ID) {
        Stall current = firstStall;
        while (current != null) {
            if (current.ID == ID) {
                return true;
            }
            current = current.relation;
        }
        return false;
    }

    private Stall findStall(int ID) {
        Stall current = firstStall;
        while (current != null) {
            if (current.ID == ID) {
                return current;
            }
            current = current.relation;
        }
        return null;
    }

    //2) Adds a grocery item to the stall with the given number.
    //If the stall with the given number does not exist, first add the stall with that number, and then put the food on it.
    public void addGroceries(int ID, String name, int code) {
        Stall goal = findStall(ID);
        Scanner in = new Scanner(System.in);
        if (goal == null) {
            System.out.print("Enter name of the hall: ");
            String nameOfHall = in .next();
            addStall(ID, nameOfHall);
            goal = findStall(ID);
        }
        Groceries temp = new Groceries(name, code);
        temp.relation = goal.content;
        goal.content = temp;
    }

    //3) Prints the numbers of stalls that have more than N items.
    public void printIDStalls(int N) {
        Stall current = firstStall;
        while (current != null) {
            int counter = 0;
            Groceries currentGroceries = current.content;
            while (currentGroceries != null) {
                counter++;
                currentGroceries = currentGroceries.relation;
            }
            if (counter > N)
                System.out.println("ID of stall is: " + current.ID);
            current = current.relation;
        }
    }

    //4) Merges the last two stalls in the given hall into one by moving the groceries from the last to the penultimate stall and deleting the last stall.
    //	 If there are not at least 2 stalls in a given hall, the merger is cancelled.
    //	 Don't take up new storage for groceries.
    public void mergeStalls(String nameOfHall) {
        Stall curr1 = firstStall;
        Stall curr2 = null;
        Stall temp = null;
        while (curr1 != null) {
            if (curr1.nameOfHall.equals(nameOfHall)) {
                if (temp == null && curr2 == null)
                    temp = curr1;
                else if (curr2 != null)
                    temp = curr2;
                curr2 = curr1;
                curr1 = curr1.relation;
            } else {
                curr1 = curr1.relation;
            }
        }
        if (temp == curr2)
            return;
        if (temp != null && curr2 != null) {
            temp.content.relation = curr2.content;
            removeStall(curr2);
        }
    }

    public boolean removeStall(Stall name) {
        if (firstStall != null && firstStall == name) {
            firstStall = firstStall.relation;
            return true;
        } else {
            Stall current, previous;
            current = firstStall;
            previous = null;
            while (current != null && current != name) {
                previous = current;
                current = current.relation;
            }
            if (current != null) {
                previous.relation = current.relation;
                return true;
            } else
                return false;
        }
    }
}
