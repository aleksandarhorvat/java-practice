/*
Write a class GameRoom that represents a list of toy boxes.

Each box has its own unique number (ID), color (for example green, blue) and the list of toys it contains.
The box contains a type (soft, wooden, plastic...) and a description (rabbit, car, cube...).
The list of toys is sorted by type.

Create and test methods that:
1) Adds a new box with the given id and color.
2) Adds a toy to the box with the given ID. A game cannot be added to a box that does not exist.
3) Returns ID of the box with the most toys of the given type.
4) Moves a toy of the given type from all but the last box to the last box. If the box is left without toys, wipe the box as well. Do not take up new memory for toys.
*/

class GameRoom {

    private Box firstBox;
    private Box lastBox;

    public class Box {
        int ID;
        String color;
        Box relation;
        Toy content;

        public Box(int ID, String color) {
            this.color = color;
            this.ID = ID;
            this.relation = null;
        }

        public String toString() {
            String result = "\n[ID: " + ID + ", Color: " + color;
            Toy current = content;
            while (current != null) {
                result += ", " + current;
                current = current.relation;
            }
            return result += "]\n---------------------------------------------------------";
        }
    }

    class Toy {
        String type;
        String description;
        Toy relation;

        public Toy(String type, String description) {
            this.type = type;
            this.description = description;
            this.relation = null;
        }

        public String toString() {
            return "\nToy: [Type: " + type + ", Description: " + description + "]";
        }
    }

    public String toString() {
        String result = "Game Room:\n---------------------------------------------------------";
        Box current = firstBox;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }

    //1) Adds a new box with the given id and color.
    public void addBox(int ID, String color) {
        Box temp = new Box(ID, color);
        if (!existBox(ID, color)) {
            temp.relation = firstBox;
            firstBox = temp;
            if (lastBox == null)
                lastBox = temp;
        }
    }

    public boolean existBox(int ID, String color) {
        Box current = firstBox;
        while (current != null) {
            if (current.ID == ID && current.color.equals(color))
                return true;
            current = current.relation;
        }
        return false;
    }

    public Box findBox(int ID) {
        Box current = firstBox;
        while (current != null) {
            if (current.ID == ID)
                return current;
            current = current.relation;
        }
        return null;
    }

    //2) Adds a toy to the box with the given ID. A game cannot be added to a box that does not exist.
    public void addToy(int ID, String type, String description) {
        Box goal = findBox(ID);
        if (goal == null)
            return;
        Toy temp = new Toy(type, description);
        if (goal.content == null || goal.content.type.compareTo(type) > 0) {
            temp.relation = goal.content;
            goal.content = temp;
        } else {
            Toy previous = goal.content;
            while (previous.relation != null && previous.relation.type.compareTo(type) <= 0) {
                previous = previous.relation;
            }
            if (previous.relation == null || previous.relation.type.compareTo(type) >= 0) {
                temp.relation = previous.relation;
                previous.relation = temp;
            }
        }
    }

    //3) Returns ID of the box with the most toys of the given type.
    public int boxWithMostToys(String type) {
        int result = 0;
        int counter = 0;
        int max = -1;
        Box current = firstBox;
        while (current != null) {
            Toy currentToy = current.content;
            while (currentToy != null) {
                if (currentToy.type.equals(type))
                    counter++;
                currentToy = currentToy.relation;
            }
            if (max == -1) {
                max = counter;
                result = current.ID;
            }
            if (max < counter) {
                max = counter;
                result = current.ID;
            }
            counter = 0;
            current = current.relation;
        }
        return result;
    }

    public boolean removeBox(Box box) {
        if (firstBox != null && firstBox == box) {
            firstBox = firstBox.relation;
            return true;
        } else {
            Box current, previous;
            current = firstBox;
            previous = null;
            while (current != null && current != box) {
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

    //4) Moves a toy of the given type from all but the last box to the last box. If the box is left without toys, wipe the box as well. Do not take up new memory for toys.
    public void moveToy(String type) {
        Box current = firstBox;
        while (current != null) {
            Toy currentToy = current.content;
            Toy temp = null;
            while (currentToy != null) {
                if (currentToy.type.equals(type)) {
                    removeToy(current, currentToy);
                    temp = currentToy;
                    currentToy = currentToy.relation;
                    addToy(lastBox, temp, type);
                } else
                    currentToy = currentToy.relation;
            }
            if (current.content == null)
                removeBox(current);
            if (current.relation == lastBox)
                current = null;
        }
    }

    public void removeToy(Box box, Toy toy) {
        if (box.content != null && box.content == toy) {
            box.content = box.content.relation;
            return;
        } else {
            Toy current, previous;
            current = box.content;
            previous = null;
            while (current != null && current != toy) {
                previous = current;
                current = current.relation;
            }
            if (current != null)
                previous.relation = current.relation;
        }
    }

    public void addToy(Box goal, Toy temp, String type) {
        if (goal.content == null || goal.content.type.compareTo(type) >= 0) {
            temp.relation = goal.content;
            goal.content = temp;
        } else {
            Toy previous = goal.content;
            while (previous.relation != null && previous.relation.type.compareTo(type) <= 0) {
                previous = previous.relation;
            }
            if (previous.relation == null || previous.relation.type.compareTo(type) >= 0) {
                temp.relation = previous.relation;
                previous.relation = temp;
            }
        }
    }
}
