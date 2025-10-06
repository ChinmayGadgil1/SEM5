import java.io.*;
import java.util.Scanner;

public class String1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        File file = new File(filename);
        int[] counts = new int[26];
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine().toLowerCase();
            for (char ch : line.toCharArray()) {
                if (ch >= 'a' && ch <= 'z') counts[ch - 'a']++;
            }
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println((char) ('A' + i) + ": " + counts[i]);
        }
        reader.close();
        sc.close();
    }
}
