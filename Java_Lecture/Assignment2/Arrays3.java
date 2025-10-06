public class Arrays3 {
     public static double average(int... numbers) {
        if (numbers.length == 0) return 0;
        int sum = 0;
        for (int num : numbers) sum += num;
        return (double) sum / numbers.length;
    }

    public static void main(String[] args) {
        System.out.println(average(1, 2, 3, 4, 5));
        System.out.println(average(10, 20));
    }
}
