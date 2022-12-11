/*
Program: The really basic restaurant kiosk menu's core class
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/12/2022
Version: 1.02
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class _core {
    static class infoItem {
        int quantityItem, priceItem;
        String nameItem;

        infoItem(int quantityItem, String nameItem, int priceItem) {
            this.quantityItem = quantityItem;
            this.nameItem = nameItem;
            this.priceItem = priceItem;
        }

    }

    // Add a pre-made menu (FOR DEMONSTRATION ONLY)
    static void addpremadeMenu() {
        Main.menuList.add(0, new _core.infoItem(1, "Ribs and Mash", 295));
        Main.menuList.add(1, new _core.infoItem(1, "Chicken ala King", 154));
        Main.menuList.add(2, new _core.infoItem(1, "Pork Cutlet", 175));
        Main.menuList.add(3, new _core.infoItem(1, "Beef Curry", 199));
        Main.menuList.add(4, new _core.infoItem(1, "Lasagna", 289));
        Main.menuList.add(5, new _core.infoItem(1, "Mediterranean Olive Pasta", 350));
        Main.menuList.add(6, new _core.infoItem(1, "Blue Lemonade", 135));
        Main.menuList.add(7, new _core.infoItem(1, "Espresso Romano", 109));
        Main.menuList.add(8, new _core.infoItem(1, "New York Cheesecake", 200));
        Main.menuList.add(9, new _core.infoItem(1, "Yogurt Cheesecake", 180));
    }

    static void printKiosk() {
        System.out.println("\n\n0 - Show the Menu");
        System.out.println("1 - Take Order");
        System.out.println("2 - Finalize Order");
        System.out.println("3 - Void an Order");
        System.out.println("4 - Cancel Order");
        System.out.println("5 - Add Menu Item");
        System.out.println("6 - Remove a Menu Item");
        System.out.println("7 - Shutdown");

        System.out.print("\nEnter choice: ");
    }

    // Outputs the ArrayList menuList
    static void printMenu() {
        System.out.printf("%-17s %-31s %s\n", "Item No.", "Name", "Price");
        for (int i = 0; i < Main.menuList.size(); i++) {
            System.out.printf("%-12s %-35s %-1s\n", (i+1), Main.menuList.get(i).nameItem, (Main.menuList.get(i).priceItem * Main.menuList.get(i).quantityItem) + " Php");
        }
    }

    // Outputs the ArrayList menuList
    static void printOrder() {
        System.out.println("\n\nCurrent Order:");
        System.out.printf("%-10s %-25s %-31s %s\n", "Item No.", "Quantity", "Name", "Price");
        for (int i = 0; i < Main.orderList.size(); i++) {
            System.out.printf("%-12s x%-17s %-35s %-1s\n", (i+1), Main.orderList.get(i).quantityItem, Main.orderList.get(i).nameItem, (Main.orderList.get(i).priceItem * Main.orderList.get(i).quantityItem) + " Php");
        }
    }

    // Take order
    static void takeOrder() {
        Scanner inputUser = new Scanner(System.in);
        // Print the food menu
        printMenu();

        System.out.print("\nItem No.: ");
        int indexItem = inputUser.nextInt();
        // Adjust index difference from menuList
        if (indexItem != 0) {
            indexItem--;
        }
        System.out.print("How many? ");
        int quantityFood = inputUser.nextInt();

        // If first item order
        if (Main.orderList.size() == 0) {
            Main.orderList.add(0, Main.menuList.get(indexItem));
            Main.orderList.get(0).quantityItem *= quantityFood;
        }
        else {
            boolean match = false;

            // Check if the latest order exist on orderList
            for (int i = 0; i < Main.orderList.size(); i++) {
                if (Objects.equals(Main.menuList.get(indexItem).nameItem, Main.orderList.get(i).nameItem)) {
                    Main.orderList.get(i).quantityItem += quantityFood;
                    match = true;
                    break;
                }
                else {
                    match = false;
                }
            }

            // If the latest order doesn't exist in the orderList
            if (!match) {
                Main.orderList.add(Main.orderList.size(), Main.menuList.get(indexItem));
                Main.orderList.get(Main.orderList.size()-1).quantityItem *= quantityFood;
            }
        }
    }

    // Finalize the whole
    static void finalizeOrder() {
        Scanner inputUser = new Scanner(System.in);
        int orderTotal = 0;

        System.out.println("\n\n1 = YES | 0 = NO");
        System.out.print("Do you want to FINALIZE the ORDER? -> ");
        // If 1 = YES
        if (inputUser.nextInt() == 1) {
            // Print final order
            System.out.println("\n\nCurrent Order (Final)");
            _core.printOrder();

            // Add price for total price
            for (int i = 0; i < Main.orderList.size(); i++) {
                orderTotal += Main.orderList.get(i).priceItem * Main.orderList.get(i).quantityItem;
            }
            System.out.println("Order Total: " + orderTotal + " Php");

            boolean enough = false;

            do {
                System.out.print("\nPayment Amount: ");
                int amtPaid = inputUser.nextInt();
                if (amtPaid > orderTotal) {
                    int change = amtPaid - orderTotal;
                    System.out.println("Amount Paid: " + amtPaid + " Php");
                    System.out.println("Change: " + change + " Php");

                    for (int i = 0; i < Main.menuList.size(); i++) {
                        Main.menuList.get(i).quantityItem = 1;
                    }
                    receiptLog(orderTotal, amtPaid, change);

                    Main.orderList.clear();
                    System.out.println("\nOrder FINALIZED.");
                    enough = true;
                }
                else {
                    System.out.println("\nThe amount paid is INSUFFICIENT!");
                    System.out.println("Finalizing order was CANCELED.");
                    break;
                }
            }
            while (!enough);
        }
    }

    // Remove item from order
    static void removeOrder() {
        Scanner inputUser = new Scanner(System.in);
        printOrder();

        System.out.print("\nSelect an order to remove: ");
        int orderRemove = inputUser.nextInt();
        // Adjust index difference from orderList
        if (orderRemove != 0) {
            orderRemove--;
        }

        Main.orderList.remove(orderRemove);
        System.out.println("\nOrder No.: " + (orderRemove+1) + " was removed.");
    }

    // Cancel the whole order
    static void cancelOrder() {
        Scanner inputUser = new Scanner(System.in);

        System.out.println("\n\n1 = YES | 0 = NO");
        System.out.print("Do you want to CANCEL the ORDER? -> ");
        if (inputUser.nextInt() == 1) {
            Main.orderList.clear();
            System.out.println("\nOrder was CANCELLED.");
        }
        else {
            System.out.println("\nOrder was NOT CANCELLED.");
        }
    }

    // Add an item to the menu
    static void addItemMenu() {
        Scanner inputUser = new Scanner(System.in);

        System.out.println("\n\n1 = YES | 0 = NO");
        System.out.print("Do you want to add another item to the menu? ");
        int choice = inputUser.nextInt();
        inputUser.nextLine();
        if (choice == 1) {
            System.out.print("ITEM NAME: ");
            String name = inputUser.nextLine();
            System.out.print("ITEM PRICE: ");
            int price = inputUser.nextInt();
            inputUser.nextLine();

            Main.menuList.add(Main.menuList.size(), new _core.infoItem(1, name, price));
            System.out.println(name + " was added to the menu");
        }
        else {
            System.out.println("Adding process was canceled.");
        }
    }

    // Remove an item from the menu
    static void removeItemMenu() {
        Scanner inputUser = new Scanner(System.in);

        System.out.println("\n\n1 = YES | 0 = NO");
        System.out.print("Do you want to REMOVE an ITEM from the MENU? -> ");
        int choice = inputUser.nextInt();
        inputUser.nextLine();
        if (choice == 1) {
            _core.printMenu();

            System.out.print("ITEM NO: ");
            // Adjust index difference from menuList
            choice = inputUser.nextInt();
            if (choice != 0) {
                choice--;
            }
            Main.menuList.remove(choice);
            inputUser.nextLine();
        }
    }

    // Print receipt w/ date and hours/mins/secs
    static void receiptLog(int total, int paid, int change) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        File receiptOrder = new File("src/main/resources/receipts/" + "receipt " + dateFormat.format(date).strip() + ".csv");

        try {
            if (receiptOrder.createNewFile()) {
                System.out.println("File created: " + receiptOrder.getName());
            }
            else {
                System.out.println("File already exists!");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw =  new FileWriter(receiptOrder);

            fw.write("Quantity,Name,Price\n");
            for (int i = 0; i < Main.orderList.size(); i++) {
                fw.write(Main.orderList.get(i).quantityItem + "," + Main.orderList.get(i).nameItem + "," + (Main.orderList.get(i).priceItem * Main.orderList.get(i).quantityItem) + '\n');
            }
            fw.write("Total:," + total + '\n');
            fw.write("Paid:," + paid + '\n');
            fw.write("Change:," + change + '\n');

            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
