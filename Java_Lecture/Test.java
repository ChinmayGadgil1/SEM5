import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);              // list = [1]
        list.add(2);              // list = [1, 2]
        list.addFirst(0);         // list = [0, 1, 2]
        list.remove((Integer) 1); // removes element 1, not index 1 → list = [0, 2]
        list.add(1, 3);           // inserts 3 at index 1 → list = [0, 3, 2]
        System.out.println(list);
    }
}

