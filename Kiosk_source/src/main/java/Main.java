/*
Program: A really basic restaurant kiosk menu
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/07/2022
Version: 1.01
 */
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    // ArrayLists
    public static ArrayList<_core.infoItem> menuList = new ArrayList<>();
    public static ArrayList<_core.infoItem> orderList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);
        int q = 1;
        boolean condition = false;

        // Add a pre-made menu (FOR DEMONSTRATION ONLY)
        _core.addpremadeMenu();

        mm:
        do {
            // Print customer current order list if there is
            if (orderList.size() != 0) {
                System.out.println("\n\nCurrent Order:");
                _core.bannerOrderList();
                for (int i = 0; i < orderList.size(); i++) {
                    _core.printOrderList(i, orderList.get(i).quantityItem, orderList.get(i).nameItem, orderList.get(i).priceItem);
                }
            }

            System.out.println("\n\n0 - Show the Menu");
            System.out.println("1 - Take Order");
            System.out.println("2 - Finalize Order");
            System.out.println("3 - Void an Order");
            System.out.println("4 - Cancel Order");
            System.out.println("5 - Add Menu Item");
            System.out.println("6 - Remove a Menu Item");
            System.out.println("7 - Shutdown");

            System.out.print("\nEnter choice: ");
            switch (inputUser.next()) {
                // Print the food menu
                case "0" -> {
                    _core.bannerMenuList();
                    for (int i = 0; i < menuList.size(); i++) {
                        _core.printMenuList(i, menuList.get(i).nameItem, menuList.get(i).priceItem);
                    }
                }

                // Take order
                case "1" -> {
                    // Print the food menu
                    _core.bannerMenuList();
                    for (int i = 0; i < menuList.size(); i++) {
                        _core.printMenuList(i, menuList.get(i).nameItem, menuList.get(i).priceItem);
                    }

                    System.out.print("\nItem No.: ");
                    int indexItem = inputUser.nextInt();
                    // Adjust index difference from menuList
                    if (indexItem != 0) {
                        indexItem--;
                    }
                    System.out.print("How many? ");
                    int quantityFood = inputUser.nextInt();

                    // If first item order
                    if (orderList.size() == 0) {
                        orderList.add(0, menuList.get(indexItem));
                        orderList.get(0).quantityItem *= quantityFood;
                    }
                    else {
                        boolean match = false;

                        // Check if the latest order exist on orderList
                        for (int i = 0; i < orderList.size(); i++) {
                            if (Objects.equals(menuList.get(indexItem).nameItem, orderList.get(i).nameItem)) {
                                orderList.get(i).quantityItem += quantityFood;
                                match = true;
                                break;
                            }
                            else {
                                match = false;
                            }
                        }

                        // If the latest order doesn't exist in the orderList
                        if (!match) {
                            orderList.add(orderList.size(), menuList.get(indexItem));
                            orderList.get(orderList.size()-1).quantityItem *= quantityFood;
                        }
                    }
                }

                case "2" -> {
                    int orderTotal = 0;

                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Do you want to FINALIZE the ORDER? -> ");
                    // If 1 = YES
                    if (inputUser.nextInt() == 1) {
                        // Print final order
                        System.out.println("\n\nCurrent Order (Final)");
                        _core.bannerOrderList();
                        for (int i = 0; i < orderList.size(); i++) {
                            _core.printOrderList(i, orderList.get(i).quantityItem, orderList.get(i).nameItem, orderList.get(i).priceItem);
                        }

                        // Add price for total price
                        for (int i = 0; i < orderList.size(); i++) {
                            orderTotal += orderList.get(i).priceItem * orderList.get(i).quantityItem;
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

                                for (int i = 0; i < menuList.size(); i++) {
                                    menuList.get(i).quantityItem = q;
                                }
                                _core.receiptLog(orderTotal, amtPaid, change);

                                orderList.clear();
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

                // Remove/Void an item from orderList
                case "3" -> {
                    System.out.println("\n\nCurrent Order:");
                    _core.bannerOrderList();
                    for (int i = 0; i < orderList.size(); i++) {
                        _core.printOrderList(i, orderList.get(i).quantityItem, orderList.get(i).nameItem, orderList.get(i).priceItem);
                    }

                    System.out.print("\nSelect an order to remove: ");
                    int orderRemove = inputUser.nextInt();
                    // Adjust index difference from orderList
                    if (orderRemove != 0) {
                        orderRemove--;
                    }

                    orderList.remove(orderRemove);
                    System.out.println("\nOrder No.: " + (orderRemove+1) + " was removed.");
                }

                // Cancel order (clear orderList)
                case "4" -> {
                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Do you want to CANCEL the ORDER? -> ");
                    if (inputUser.nextInt() == 1) {
                        orderList.clear();
                        System.out.println("\nOrder was CANCELLED.");
                    }
                    else {
                        System.out.println("\nOrder was NOT CANCELLED.");
                    }
                }

                // Add an item to menuList
                case "5" -> {
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

                        menuList.add(menuList.size(), new _core.infoItem(q, name, price));
                        System.out.println(name + " was added to the menu");
                    }
                    else {
                        System.out.println("Adding process was canceled.");
                    }
                }

                // Remove an item from te menuList
                case "6" -> {
                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Do you want to REMOVE an ITEM from the MENU? -> ");
                    int choice = inputUser.nextInt();
                    inputUser.nextLine();
                    if (choice == 1) {
                        _core.bannerMenuList();
                        for (int i = 0; i < menuList.size(); i++) {
                            _core.printMenuList(i, menuList.get(i).nameItem, menuList.get(i).priceItem);
                        }

                        System.out.print("ITEM NO: ");
                        // Adjust index difference from menuList
                        choice = inputUser.nextInt();
                        if (choice != 0) {
                            choice--;
                        }
                        menuList.remove(choice);
                        inputUser.nextLine();
                    }
                }

                case "7" -> {
                    break mm;
                }

                default -> {
                    System.out.println("Invalid Input!");
                }
            }
        }
        while (!condition);

        System.out.println("\n\nProgram was shutdown");
        System.exit(0);
    }
}
