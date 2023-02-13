class List {

    class Element {
        double info;
        Element relation;

        public Element(double info) {
            this.info = info;
            this.relation = null;
        }

        public String toString() {
            return info + "";
        }
    }

    Element first;
    Element last;
    int numberOfElements;

    public List() {
        this.first = null;
        this.last = null;
        this.numberOfElements = 0;
    }

    public int size() {
        return numberOfElements;
    }

    //Add new element to first place in list
    public void addToStart(double number) {
        Element temp = new Element(number);
        temp.relation = first;
        first = temp;
        if (last == null)
            last = temp;
        numberOfElements++;
    }

    public void addToEnd(int number) {
        if (last == null)
            addToStart(number);
        else {
            Element temp = new Element(number);
            last.relation = temp;
            last = temp;
        }
    }

    //Print all elements of the list
    public void printAllElements() {
        if (first == null) {
            System.out.println("The list is empty");
        } else {
            System.out.println("Content of the list:");
            Element current = first;
            while (current != null) {
                System.out.println(current.info);
                current = current.relation;
            }
            System.out.println();
        }
    }

    public String toString() {
        String rez = "List [ ";
        Element current = first;
        while (current != null) {
            rez += current.info + " ";
            current = current.relation;
        }
        rez += "]";
        return rez;
    }

    //Return the minimum of the list, in case of empty list return the max value of the double data type
    public double minimum() {
        double min = Double.MAX_VALUE;
        Element current = first;
        while (current != null) {
            if (current.info < min) {
                min = current.info;
            }
            current = current.relation;
        }
        return min;
    }

    //Returns if the numbers exist in the list
    public boolean inList(double number) {
        Element current = first;
        while (current != null && current.info != number)
            current = current.relation;
        return current != null;
    }

    //Returns the value of the element on given position
    public double element(int k) {
        if (k < 0)
            throw new IndexOutOfBoundsException("There are no elements on negative positions!");
        Element current = first;
        int counter = 0;
        while (current != null && k > counter) {
            current = current.relation;
            counter++;
        }
        if (current != null)
            return current.info;
        else
            throw new IndexOutOfBoundsException("The list is shorter than: " + k);
    }

    //Add the element on given position
    public void add(double number, int position) {
        Element temp = new Element(number);
        if (position < 0)
            position = 0;

        if (position > numberOfElements)
            position = numberOfElements;

        if (position == 0) {
            temp.relation = first;
            first = temp;
            numberOfElements++;
        } else {
            Element previous = first;
            int counter = 0;
            while (previous != null && counter < position - 1) {
                previous = previous.relation;
                counter++;
            }
            temp.relation = previous.relation;
            previous.relation = temp;
            numberOfElements++;
        }
    }

    //Remove one element from the list
    public boolean removeFromList(double number) {
        if (first != null && first.info == number) {
            first = first.relation;
            numberOfElements--;
            return true;
        } else {
            Element current, previous;
            current = first;
            previous = null;
            while (current != null && current.info != number) {
                previous = current;
                current = current.relation;
            }
            if (current != null) {
                previous.relation = current.relation;
                numberOfElements--;
                return true;
            } else
                return false;
        }
    }

    //Remove more elements
    public int removeFromListMore(double number) {
        int counter = 0;
        while (first != null && first.info == number) {
            first = first.relation;
            counter++;
        }
        if (first != null) {
            Element current = first;
            while (current.relation != null) {
                Element previous = current;
                current = current.relation;
                if (current.info == number) {
                    previous.relation = current.relation;
                    counter++;
                    current = previous;
                }
            }
        }
        numberOfElements -= counter;
        return counter;
    }

    public boolean removeK(int k) {
        if (k >= numberOfElements)
            return false;
        if (k < 0)
            return false;

        if (k == 0)
            first = first.relation;

        else {
            Element previous = first;
            int counter = 0;
            while (k - 1 > counter) {
                previous = previous.relation;
                counter++;
            }
            previous.relation = previous.relation.relation;
        }
        numberOfElements--;
        return true;
    }

    public void twistList() {
        if (first == null || first.relation == null)
            return;
        Element previous = null;
        Element current = first;
        while (current != null) {
            Element next = current.relation;
            current.relation = previous;
            previous = current;
            current = next;
        }
        first = previous;
    }

    public List getFirstN(int n) {
        List result = new List();
        if (n <= 0)
            return result;
        result.first = first;
        int counter = 0;
        Element last = first;
        while (last != null && counter < n - 1) {
            last = last.relation;
            counter++;
        }
        if (last != null) {
            first = last.relation;
            last.relation = null;
        } else
            first = null;
        return result;
    }

    public List getEven() {
        List even = new List();
        Element evenLast = null;
        Element current, previous;
        while (first != null && first.info % 2 == 0) {
            current = first;
            first = first.relation;
            if (even.first == null) {
                even.first = current;
                evenLast = current;
                current.relation = null;
            } else {
                evenLast.relation = current;
                current.relation = null;
                evenLast = current;
            }
        }
        if (first != null) {
            current = first;
            while (current.relation != null) {
                previous = current;
                current = current.relation;
                if (current.info % 2 == 0) {
                    previous.relation = current.relation;
                    if (even.first == null) {
                        even.first = current;
                        current.relation = null;
                        evenLast = current;
                    } else {
                        evenLast.relation = current;
                        current.relation = null;
                        evenLast = current;
                    }
                    current = previous;
                }
            }
        }
        return even;
    }
}
