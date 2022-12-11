/*
Program: A really basic restaurant kiosk menu
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/12/2022
Version: 1.02
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // ArrayLists
    public static ArrayList<_core.infoItem> menuList = new ArrayList<>();
    public static ArrayList<_core.infoItem> orderList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);
        boolean condition = false;

        // Add a pre-made menu (FOR DEMONSTRATION ONLY)
        _core.addpremadeMenu();

        mm:
        do {
            // Print customer current order list if there is
            if (orderList.size() != 0) {
                _core.printOrder();
            }

            // Print kiosk menu
            _core.printKiosk();

            switch (inputUser.next()) {
                // Print the food menu
                case "0" ->
                    _core.printMenu();

                // Take order
                case "1" ->
                    _core.takeOrder();

                // Finalize Order
                case "2" ->
                    _core.finalizeOrder();

                // Remove/Void an item from orderList
                case "3" ->
                    _core.removeOrder();

                // Cancel order (clear orderList)
                case "4" ->
                    _core.cancelOrder();

                // Add an item to menuList
                case "5" ->
                    _core.addItemMenu();

                // Remove an item from the menuList
                case "6" ->
                    _core.removeItemMenu();

                case "7" -> {
                    break mm;
                }

                default ->
                    System.out.println("Invalid Input!");
            }
        }
        while (!condition);

        System.out.println("\n\nProgram was shutdown");
        System.exit(0);
    }
}