

import org.example.ArrayListExample;
import org.example.LinkedListExample;
import org.example.StackExample;
import org.example.VectorExample;

import java.util.Scanner;


public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu chính:");
            System.out.println("1. ArrayList");
            System.out.println("2. LinkedList");
            System.out.println("3. Vector");
            System.out.println("4. Stack");
            System.out.println("5. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    ArrayListExample.main(new String[]{});
                    break;
                case 2:
                    LinkedListExample.main(new String[]{});
                    break;
                case 3:
                    VectorExample.main(new String[]{});
                    break;
                case 4:
                    StackExample.main(new String[]{});
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}