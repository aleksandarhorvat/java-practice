/*
The Street class remembers the list of houses in the street.
The serial number is remembered for the house and a list of people living in it is attached.
The list of houses is sorted by serial numbers and the serial numbers are of course unique.
For a person, the personal name is remembered as a single String.
People with the same name can live in the same house.

Create and test methods that:
1) Adds a new house with the given number to the list.
2) Adds a person to the house with the given number. A person cannot be added to a house that does not exist, you should give up adding a person if the house does not exist.
3) Returns the number of the house with the most inhabitants with a name longer than the given N.
4) Moves the last person from the given house A to the house with number B.
   The house can be empty, ie it should not be removed if there are no more people in it. If the house with number B does not exist, remove the person from the list completely.
*/

class Street {

    private House firstHouse;

    class House {
        int serialNumber;
        Person content;
        House relation;

        public House(int serialNumber) {
            this.serialNumber = serialNumber;
            this.relation = null;
        }

        public String toString() {
            String result = "\n[Serial number of the house: " + serialNumber;
            Person current = content;
            while (current != null) {
                result += " " + current;
                current = current.relation;
            }
            return result += "]\n---------------------------------------------------------";
        }
    }

    class Person {
        String name;
        Person relation;

        public Person(String name) {
            this.name = name;
            this.relation = null;
        }

        public String toString() {
            return "\n[Name: " + name + "]";
        }
    }

    public String toString() {
        String result = "House:\n---------------------------------------------------------";
        House current = firstHouse;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }

    //1) Adds a new house with the given number to the list.
    public void addHouse(int serialNumber) {
        if (!findHouse(serialNumber)) {
            if (firstHouse == null || firstHouse.serialNumber > serialNumber) {
                House temp = new House(serialNumber);
                temp.relation = firstHouse;
                firstHouse = temp;
            } else {
                if (firstHouse != null && firstHouse.serialNumber < serialNumber) {
                    House current = firstHouse;
                    House previous = firstHouse;
                    while (previous.relation != null && previous.relation.serialNumber < serialNumber) {
                        previous = current;
                        current = current.relation;
                    }
                    if (previous.relation == null || previous.relation.serialNumber > serialNumber) {
                        House temp = new House(serialNumber);
                        temp.relation = previous.relation;
                        previous.relation = temp;
                    }
                }
            }
        }
    }

    public boolean findHouse(int serialNumber) {
        House current = firstHouse;
        while (current != null) {
            if (current.serialNumber == serialNumber)
                return true;
            current = current.relation;
        }
        return false;
    }

    public House returnHouse(int serialNumber) {
        House current = firstHouse;
        while (current != null) {
            if (current.serialNumber == serialNumber)
                return current;
            current = current.relation;
        }
        return null;
    }

    //2) Adds a person to the house with the given number. A person cannot be added to a house that does not exist, you should give up adding a person if the house does not exist.
    public void addPerson(int serialNumber, String name) {
        House goal = returnHouse(serialNumber);
        if (goal == null)
            return;
        Person temp = new Person(name);
        temp.relation = goal.content;
        goal.content = temp;
    }

    //3) Returns the number of the house with the most inhabitants with a name longer than the given N.
    public int returnNumberHouse(int N) {
        int counter = 0;
        int max = 0;
        int numberHouse = -1;
        House current = firstHouse;
        while (current != null) {
            counter = 0;
            Person currentPerson = current.content;
            while (currentPerson != null) {
                if (currentPerson.name.length() > N)
                    counter++;
                currentPerson = currentPerson.relation;
            }
            if (max < counter) {
                max = counter;
                numberHouse = current.serialNumber;
            }
            current = current.relation;
        }
        return numberHouse;
    }

    //4) Moves the last person from the given house A to the house with number B.
    //	 The house can be empty, ie it should not be removed if there are no more people in it. If the house with number B does not exist, remove the person from the list completely.
    public void movePerson(int numberHouseA, int numberHouseB) {
        House A = returnHouse(numberHouseA);
        Person currentPerson = A.content;
        while (currentPerson.relation != null) {
            currentPerson = currentPerson.relation;
        }
        House B = returnHouse(numberHouseB);
        if (B.content == null) {
            removePerson(A, currentPerson);
            return;
        }
        removePerson(A, currentPerson);
        currentPerson.relation = B.content;
        B.content = currentPerson;
    }

    public void removePerson(House house, Person person) {
        if (house.content != null && house.content == person) {
            house.content = house.content.relation;
            return;
        } else {
            Person current = house.content;
            Person previous = null;
            while (current != null && current != person) {
                previous = current;
                current = current.relation;
            }
            if (current != null)
                previous.relation = current.relation;
        }
    }
}
