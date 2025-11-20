import java.io.*;

class FileAccess {
    private final String filename = "sync_demo.txt";

    // synchronized write method
    public synchronized void writeToFile(String data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");
            System.out.println(Thread.currentThread().getName() + " wrote: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // synchronized read method
    public synchronized void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println(Thread.currentThread().getName() + " is reading:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class SyncFileDemo {
    public static void main(String[] args) {
        FileAccess fileAccess = new FileAccess();

        // Writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                fileAccess.writeToFile("Line " + i + " from Writer");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WriterThread");

        // Reader thread
        Thread readerThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                fileAccess.readFromFile();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ReaderThread");

        writerThread.start();
        readerThread.start();
    }
}