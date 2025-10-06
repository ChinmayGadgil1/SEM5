public class Arrays2 {
    public static int average(int[] array) {
        int sum = 0;
        for (int num : array) sum += num;
        return sum / array.length;
    }

    public static double average(double[] array) {
        double sum = 0;
        for (double num : array) sum += num;
        return sum / array.length;
    }

    public static void main(String[] args) {
        int[] intArr = {1, 2, 3, 4, 5};
        double[] doubleArr = {1.5, 2.5, 3.5};
        System.out.println(average(intArr));
        System.out.println(average(doubleArr));
    }
}
