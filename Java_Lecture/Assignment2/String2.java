import java.io.*;
public class String2 {
    public static void main(String[] args) throws IOException {
        File file = new File("String2.txt");
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Name: " + file.getName());
        // System.out.println("Path: " + file.getPath());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Exists: " + file.exists());
        System.out.println("Can Read: " + file.canRead());
        System.out.println("Can Write: " + file.canWrite());
        // System.out.println("Is Directory: " + file.isDirectory());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Absolute: " + file.isAbsolute());
        System.out.println("Length: " + file.length());
        System.out.println("Last Modified: " + file.lastModified());
        System.out.println("Free Space: " + file.getFreeSpace());
        System.out.println("Total Space: " + file.getTotalSpace());
        System.out.println("Usable Space: " + file.getUsableSpace());
        file.delete();
    }
}