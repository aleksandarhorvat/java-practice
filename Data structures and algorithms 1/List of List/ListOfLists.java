class ListOfLists {

    private List firstList;
    private List lastList;
    int numberOfLists;

    class List {
        String name;
        List relation;
        Element content;
        public int numberOfElements;

        public List(String name) {
            this.name = name;
            this.relation = null;
            numberOfElements = 0;
        }

        public String toString() {
            String result = "\n[Info: " + name;
            Element current = content;
            while (current != null) {
                result += ", " + current;
                current = current.relation;
            }
            return result += "]\n---------------------------------------------------------";
        }
    }

    class Element {
        int info;
        Element relation;

        public Element(int info) {
            this.info = info;
            this.relation = null;
        }

        public String toString() {
            return "\n[Element: " + info + "]";
        }
    }

    public String toString() {
        String result = "Lists:\n---------------------------------------------------------";
        List current = firstList;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }


    public void addList(String name) {
        if (!listExist(name)) {
            List temp = new List(name);
            temp.relation = firstList;
            firstList = temp;
            if (lastList == null)
                lastList = temp;
            numberOfLists++;
        }
    }

    public void addSortedList(String name) {
        List temp = new List(name);
        if (firstList == null || firstList.name.compareTo(name) > 0) {
            temp.relation = firstList;
            firstList = temp;
            if (lastList == null)
                lastList = temp;
            numberOfLists++;
        } else {
            if (!firstList.name.equals(name)) {
                List previous = firstList;
                while (previous.relation != null && previous.relation.name.compareTo(name) < 0)
                    previous = previous.relation;
                if (previous.relation == null || !(previous.relation.name.compareTo(name) < 0)) {
                    temp.relation = previous.relation;
                    previous.relation = temp;
                }
            }
        }
    }

    public void addSortedListWithRepetition(String name) {
        List temp = new List(name);
        if (firstList == null || firstList.name.compareTo(name) > 0) {
            temp.relation = firstList;
            firstList = temp;
            if (lastList == null)
                lastList = temp;
            numberOfLists++;
        } else {
            List previous = firstList;
            while (previous.relation != null && previous.relation.name.compareTo(name) <= 0)
                previous = previous.relation;
            if (previous.relation == null || !(previous.relation.name.compareTo(name) <= 0)) {
                temp.relation = previous.relation;
                previous.relation = temp;
            }
        }
    }

    public boolean listExist(String name) {
        List current = firstList;
        while (current != null) {
            if (current.name.equals(name))
                return true;
            current = current.relation;
        }
        return false;
    }

    public List findList(String name) {
        List current = firstList;
        while (current != null) {
            if (current.name.equals(name))
                return current;
            current = current.relation;
        }
        return null;
    }

    public boolean elementExist(List current, int info) {
        Element temp = current.content;
        while (temp != null) {
            if (temp.info == info)
                return true;
            temp = temp.relation;
        }
        return false;
    }

    public void addElement(String name, int info) {
        List current = findList(name);
        if (current == null) {
            //return; - if we don't do anything
            addList(name);
            current = findList(name);
        }
        if (!elementExist(current, info)) {
            Element temp = new Element(info);
            temp.relation = current.content;
            current.content = temp;
        }
        current.numberOfElements++;
    }

    public void addElementWithPosition(String name, int info, int position) {
        int counter = 0;
        List current = findList(name);
        if (current == null) {
            //return; - if we don't do anything
            addList(name);
            current = findList(name);
        }
        Element temp = new Element(info);
        if (position < 0)
            position = 0;

        if (position > current.numberOfElements)
            position = numberOfLists;

        if (position == 0) {
            temp.relation = current.content;
            current.content = temp;
            current.numberOfElements++;
        } else {
            Element previous = current.content;
            while (previous != null && position < counter - 1) {
                previous = previous.relation;
                counter++;
            }
            temp.relation = previous.relation;
            previous.relation = temp;
            current.numberOfElements++;
        }
    }

    public boolean removeList(List list) {
        if (firstList != null && firstList == list) {
            firstList = firstList.relation;
            return true;
        } else {
            List current, previous;
            current = firstList;
            previous = null;
            while (current != null && current != list) {
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

    public boolean removeElement(List list, Element element) {
        if (list.content != null && list.content == element) {
            list.content = list.content.relation;
            return true;
        } else {
            Element current, previous;
            current = list.content;
            previous = null;
            while (current != null && current != element) {
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
