import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Target: ");
        String target = scanner.next();
        System.out.println("Start: ");
        int start = scanner.nextInt();
        System.out.println("End: ");
        int end = scanner.nextInt();

        // Ensure scanner is closed to prevent resource leaks
        scanner.close();

        // Validate port range
        if (start < 1 || end < 1 || end > 65535 || start > end) {
            System.out.println("Invalid port range");
            return;
        }

        for (int port = start; port <= end; port++) {
            if (isPortOpen(target, port)) {
                System.out.println("Port: " + port + " Open");
            } else {
                System.out.println("Port: " + port + " Closed");
            }
        }
    }

    public static boolean isPortOpen(String target, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(target, port), 1000); // 1000ms timeout
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
