import java.net.*;
import java.io.*;

public class URLClassDemo {
    public static void main(String[] args) throws Exception {
        // Create URL object
        URL url = new URL("https://www.example.com:8080/docs/page.html?id=123#section");
        
        // Get URL components
        System.out.println("=== URL Components ===");
        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("Default Port: " + url.getDefaultPort());
        System.out.println("Path: " + url.getPath());
        System.out.println("File: " + url.getFile());
        System.out.println("Query: " + url.getQuery());
        System.out.println("Ref (Anchor): " + url.getRef());
        
        // Read content from URL
        System.out.println("\n=== Reading URL Content ===");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            
            String line;
            int count = 0;
            System.out.println("First 5 lines of content:");
            while ((line = reader.readLine()) != null && count < 5) {
                System.out.println(line);
                count++;
            }
        }
        
        // URLConnection for more control
        System.out.println("\n=== URLConnection Info ===");
        URLConnection conn = url.openConnection();
        System.out.println("Content Type: " + conn.getContentType());
        System.out.println("Content Length: " + conn.getContentLength());
        
        // Compare URLs
        URL url1 = new URL("https://google.com");
        URL url2 = new URL("https://google.com:80");
        System.out.println("\nURLs equal? " + url1.equals(url2));
    }
}