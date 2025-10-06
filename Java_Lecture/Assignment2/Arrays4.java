import java.util.Arrays;

public class Arrays4 {
    public static int[] doubleCapacity(int[] list) {
        int[] newList = new int[list.length * 2];
        for (int i = 0; i < list.length; i++) newList[i] = list[i];
        return newList;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] doubled = doubleCapacity(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(doubled));
    }
}
