import java.util.Scanner;

public class Arrays5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int sum = 0;
        for (int[] row : matrix) {
            for (int val : row) sum += val;
        }
        System.out.println(sum);
        sc.close();
    }
}
