/*
Write a class ComputerClassrooms that remembers data about classrooms and their equipment.

The primary list of elements is classrooms only. They are stored in a singly linked list.
For each classroom, the color of the walls is remembered (which is the most important information for a computer classroom) and the list of equipment in it.
Classroom numbers are not remembered within the class, but are determined implicitly based on their position in the main list and can change as classrooms are added and dropped.
Multiple classrooms in the list can have the same color. New classrooms are added to the end of the list.

Pieces of equipment are remembered within an individual classroom as a singly linked list.
There can be several of the same piece of equipment.
For each piece of equipment, the type and short description are remembered (for example type "monitor", description "LG", type "computer", description "Dell2015").
The pieces of equipment are not unique, that is, they can be repeated in the list.
For easier searching and printing, the pieces of equipment are sorted by type of equipment in the list.

Create methods for (and helper if necessary):
1) Adding a new classroom to the classroom list, passing the color as a parameter.
2) Adding a piece of equipment (with the given type and description) to the classroom with the passed number.
   If that number does not exist, cancel the addition. Return information about the success of the addition (boolean).
3) Based on the parameters type, description, color and n, add a new piece of equipment to the n-th classroom with the given color. If there is no such classroom, give up.
4) Write all pieces of equipment in classrooms of a given color
5) Count how many types of equipment there are in all the classrooms together
6) Add a piece of equipment with the given type and description to the most empty classroom
7) Remove one piece of equipment of the given type from the numbered classroom.
8) Remove one piece of equipment of the given type and description from any classroom of the classroom of the given color.
9) Remove all equipment of the given type from the classroom with the given number
10) Transfer all equipment of the specified type from the classroom with the most equipment of that type to the classroom with the least equipment of that type.
*/

class ComputerClassrooms {

    private Classrooom firstClassroom;
    private Classrooom lastClassroom;

    class Classrooom {
        String color;
        Equipment content;
        Classrooom relation;

        public Classrooom(String color) {
            this.color = color;
            this.relation = null;
        }

        public String toString() {
            String result = "\n[Color: " + color;
            Equipment current = content;
            while (current != null) {
                result += " " + current;
                current = current.relation;
            }
            return result += "]\n---------------------------------------------------------";
        }
    }

    class Equipment {
        String type;
        String description;
        Equipment relation;

        public Equipment(String type, String description) {
            this.type = type;
            this.description = description;
            this.relation = null;
        }

        public String toString() {
            return "\n[Type: " + type + ", Description: " + description + "]";
        }
    }

    public String toString() {
        String result = "Classroom:\n---------------------------------------------------------";
        Classrooom current = firstClassroom;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }

    //1) Adding a new classroom to the classroom list, passing the color as a parameter.
    public void addClassroom(String color) {
        if (lastClassroom == null) {
            Classrooom temp = new Classrooom(color);
            temp.relation = firstClassroom;
            firstClassroom = temp;
            lastClassroom = temp;
        } else {
            Classrooom temp = new Classrooom(color);
            lastClassroom.relation = temp;
            lastClassroom = temp;
        }
    }

    public Classrooom findClassroom(int numberClassroom) {
        Classrooom curr = firstClassroom;
        int temp = 1;
        while (curr != null) {
            if (temp == numberClassroom)
                return curr;
            else {
                temp++;
                curr = curr.relation;
            }
        }
        return null;
    }

    public Classrooom findClassroomColor(int numberClassroom, String color) {
        Classrooom curr = firstClassroom;
        int temp = 0;
        while (curr != null) {
            if (curr.color.equals(color)) {
                temp++;
                if (temp == numberClassroom)
                    return curr;
                else
                    curr = curr.relation;
            } else
                curr = curr.relation;
        }
        return null;
    }

    //2) Adding a piece of equipment (with the given type and description) to the classroom with the passed number.
    //	 If that number does not exist, cancel the addition. Return information about the success of the addition (boolean).
    public boolean addEquipment(String type, String description, int numberClassroom) {
        if (findClassroom(numberClassroom) == null) {
            return false;
        }
        Classrooom goal = findClassroom(numberClassroom);
        Equipment temp = new Equipment(type, description);
        if (goal.content == null || goal.content.type.compareTo(type) >= 0) {
            temp.relation = goal.content;
            goal.content = temp;
            return true;
        } else {
            Equipment previous = goal.content;
            while (previous.relation != null && previous.relation.type.compareTo(type) <= 0) {
                previous = previous.relation;
            }
            if (previous.relation == null || previous.relation.type.compareTo(type) >= 0) {
                temp.relation = previous.relation;
                previous.relation = temp;
                return true;
            }
        }
        return false;
    }

    //3) Based on the parameters type, description, color and n, add a new piece of equipment to the n-th classroom with the given color. If there is no such classroom, give up.
    public void addEquipmentN(String type, String description, String colorClassroom, int numberClassroom) {
        if (findClassroomColor(numberClassroom, colorClassroom) == null) {
            return;
        }
        Classrooom goal = findClassroomColor(numberClassroom, colorClassroom);
        Equipment temp = new Equipment(type, description);
        if (goal.content == null || goal.content.type.compareTo(type) >= 0) {
            temp.relation = goal.content;
            goal.content = temp;
        } else {
            Equipment previous = goal.content;
            while (previous.relation != null && previous.relation.type.compareTo(type) <= 0) {
                previous = previous.relation;
            }
            if (previous.relation == null || previous.relation.type.compareTo(type) >= 0) {
                temp.relation = previous.relation;
                previous.relation = temp;
            }
        }
    }

    //4) Write all pieces of equipment in classrooms of a given color
    public void printEquipment(String color) {
        Classrooom curr = firstClassroom;
        while (curr != null) {
            if (curr.color.equals(color))
                System.out.println(curr);
            curr = curr.relation;
        }
    }

    //5) Count how many types of equipment there are in all the classrooms together
    public int numberEquipment(String type) {
        Classrooom curr = firstClassroom;
        int count = 0;
        while (curr != null) {
            Equipment currEquipment = curr.content;
            while (currEquipment != null) {
                if (currEquipment.type.equals(type))
                    count++;
                currEquipment = currEquipment.relation;
            }
            curr = curr.relation;
        }
        return count;
    }

    public Classrooom findLeast() {
        Classrooom curr = firstClassroom;
        Classrooom goal = null;
        int min = -1;
        while (curr != null) {
            Equipment currEquipment = curr.content;
            int count = 0;
            while (currEquipment != null) {
                count++;
                currEquipment = currEquipment.relation;
            }
            if (min == -1) {
                min = count;
                goal = curr;
            }
            if (min > count) {
                min = count;
                goal = curr;
            }
            curr = curr.relation;
        }
        return goal;
    }

    public Classrooom findMostOfType(String type) {
        Classrooom curr = firstClassroom;
        Classrooom goal = null;
        int max = 0;
        while (curr != null) {
            Equipment currEquipment = curr.content;
            int count = 0;
            while (currEquipment != null) {
                if (currEquipment.type.equals(type))
                    count++;
                currEquipment = currEquipment.relation;
            }
            if (max <= count) {
                max = count;
                goal = curr;
            }
            curr = curr.relation;
        }
        return goal;
    }

    public Classrooom findLeastOfType(String type) {
        Classrooom curr = firstClassroom;
        Classrooom goal = null;
        int min = -1;
        while (curr != null) {
            Equipment currEquipment = curr.content;
            int count = 0;
            while (currEquipment != null) {
                if (currEquipment.type.equals(type))
                    count++;
                currEquipment = currEquipment.relation;
            }
            if (min == -1) {
                min = count;
                goal = curr;
            }
            if (min > count) {
                min = count;
                goal = curr;
            }
            curr = curr.relation;
        }
        return goal;
    }

    //6) Add a piece of equipment with the given type and description to the most empty classroom
    public void addEquipmentToLeast(String type, String description) {
        Classrooom goal = findLeast();
        Equipment temp = new Equipment(type, description);
        if (goal.content == null || goal.content.type.compareTo(type) >= 0) {
            temp.relation = goal.content;
            goal.content = temp;
        } else {
            Equipment previous = goal.content;
            while (previous.relation != null && previous.relation.type.compareTo(type) <= 0) {
                previous = previous.relation;
            }
            if (previous.relation == null || previous.relation.type.compareTo(type) >= 0) {
                temp.relation = previous.relation;
                previous.relation = temp;
            }
        }
    }

    //7) Remove one piece of equipment of the given type from the numbered classroom.
    public void removeEquipment(String type, int numberClassroom) {
        if (findClassroom(numberClassroom) == null)
            return;
        Classrooom goal = findClassroom(numberClassroom);
        Equipment curr = goal.content;
        while (curr != null) {
            if (curr.type.equals(type)) {
                removeEquipment(goal, curr);
                return;
            }
            curr = curr.relation;
        }
    }

    //8) Remove one piece of equipment of the given type and description from any classroom of the classroom of the given color.
    public void removeEquipmentColor(String type, String description, String colorClassroom) {
        if (findClassroomColor(1, colorClassroom) == null)
            return;
        Classrooom goal = findClassroomColor(1, colorClassroom);
        Equipment curr = goal.content;
        while (curr != null) {
            if (curr.type.equals(type) && curr.description.equals(description)) {
                removeEquipment(goal, curr);
                return;
            }
            curr = curr.relation;
        }
    }

    //9) Remove all equipment of the given type from the classroom with the given number
    public void removeAllEquipment(String type, int numberClassroom) {
        if (findClassroom(numberClassroom) == null)
            return;
        Classrooom goal = findClassroom(numberClassroom);
        Equipment curr = goal.content;
        while (curr != null) {
            if (curr.type.equals(type)) {
                removeEquipment(goal, curr);
            }
            curr = curr.relation;
        }
    }

    //10) Transfer all equipment of the specified type from the classroom with the most equipment of that type to the classroom with the least equipment of that type.
    public void moveEquipment(String type) {
        Classrooom start = findMostOfType(type);
        Classrooom goal = findLeastOfType(type);
        if (start == null && goal == null)
            return;
        Equipment startEquipment = start.content;
        Equipment temp = null;
        while (startEquipment != null) {
            if (startEquipment.type.equals(type)) {
                removeEquipment(start, startEquipment);
                temp = startEquipment;
                startEquipment = startEquipment.relation;
                addEquipment(goal, temp, type);
            } else
                startEquipment = startEquipment.relation;
        }
    }

    public void removeEquipment(Classrooom classroom, Equipment equipment) {
        if (classroom.content != null && classroom.content == equipment) {
            classroom.content = classroom.content.relation;
            return;
        } else {
            Equipment current, previous;
            current = classroom.content;
            previous = null;
            while (current != null && current != equipment) {
                previous = current;
                current = current.relation;
            }
            if (current != null)
                previous.relation = current.relation;
        }
    }

    public void addEquipment(Classrooom goal, Equipment temp, String type) {
        if (goal.content == null || goal.content.type.compareTo(type) >= 0) {
            temp.relation = goal.content;
            goal.content = temp;
        } else {
            Equipment previous = goal.content;
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
