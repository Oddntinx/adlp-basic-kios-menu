// may whoever you worship have mercy upon your soul - A.D.L.P. (The guy who wrote this)

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Core.infoFood> menuList = new ArrayList<>();
        ArrayList<Core.infoFood> custmrOrder = new ArrayList<>();

        Scanner inputUser = new Scanner(System.in);
        boolean condition = false;

        // Pre-made Menu
        menuList.add(0, new Core.infoFood(1, "Ribs and Mash", 295));
        menuList.add(1, new Core.infoFood(1, "Chicken ala King", 154));
        menuList.add(2, new Core.infoFood(1, "Pork Cutlet", 175));
        menuList.add(3, new Core.infoFood(1, "Beef Curry", 199));
        menuList.add(4, new Core.infoFood(1, "Lasagna", 289));
        menuList.add(5, new Core.infoFood(1, "Mediterranean Olive Pasta", 350));
        menuList.add(6, new Core.infoFood(1, "Blue Lemonade", 135));
        menuList.add(7, new Core.infoFood(1, "Espresso Romano", 109));
        menuList.add(8, new Core.infoFood(1, "New York Cheesecake", 200));
        menuList.add(9, new Core.infoFood(1, "Yogurt Cheesecake", 180));

        mm:
        do {
            // Print Customer/s Order
            if (custmrOrder.size() != 0) {
                System.out.println("\n\nCurrent Order");
                for (int i = 0; i < custmrOrder.size(); i++) {
                    Core.printList(custmrOrder.size(), custmrOrder.get(i).quantityItem, custmrOrder.get(i).nameItem, custmrOrder.get(i).priceItem);
                }
            }

            System.out.println("\n\n1 - Take Order");
            System.out.println("2 - Finalize Order");
            System.out.println("3 - Void an Order");
            System.out.println("4 - Cancel Order");
            System.out.println("5 - Add Menu Item");
            System.out.println("6 - Shutdown");

            System.out.print("\nEnter choice: ");
            // String choiceMM = inputUser.next();
            switch (inputUser.next()) {

                // Take Order
                case "1" -> {
                    // Print the Menu
                    System.out.println("\tName\tPrice");
                    for (int i = 0; i < menuList.size(); i++) {
                        System.out.println((i+1) + ")\t\t" + menuList.get(i).nameItem + "\t\t\t\t" + menuList.get(i).priceItem);
                    }

                    System.out.print("\nEnter Order: ");
                    int indexItem = inputUser.nextInt();
                    if (indexItem != 0) {
                        indexItem--;
                    }
                    System.out.print("How many? ");
                    int quantItem = inputUser.nextInt();

                    // If there are no orders
                    if (custmrOrder.size() == 0) {
                        custmrOrder.add(0, menuList.get(indexItem));

                        if (custmrOrder.get(0).quantityItem > 1) {
                            custmrOrder.get(0).quantityItem *= quantItem;
                        }
                        else {
                            custmrOrder.get(0).quantityItem *= quantItem;
                        }
                    }
                    else {
                        boolean match = false;
                        // Check if there is already a similar order in the list
                        for (int i = 0; i < custmrOrder.size(); i++) {
                            if (Objects.equals(menuList.get(indexItem).nameItem, custmrOrder.get(i).nameItem)) {
                                custmrOrder.get(i).quantityItem += quantItem;
                                match = true;
                                break;
                            }
                            else {
                                match = false;
                            }
                        }

                        // If the order doesn't match existing order
                        if (!match) {
                            custmrOrder.add(custmrOrder.size(), menuList.get(indexItem));
                            custmrOrder.get(custmrOrder.size()-1).quantityItem *= quantItem;
                        }

                    }
                }

                // Finalize Order
                case "2" -> {
                    int totalOrder = 0;

                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Do you want to finalize the Order? ");
                    if (inputUser.nextInt() == 1) {
                        System.out.println("\n\nCurrent Order (Final)");
                        for (int i = 0; i < custmrOrder.size(); i++) {
                            System.out.println(custmrOrder.get(i).quantityItem + "x\t\t" + custmrOrder.get(i).nameItem + "\t\t\t\t" + (custmrOrder.get(i).priceItem * custmrOrder.get(i).quantityItem) + " Php");
                        }

                        // Add everything
                        for (int i = 0; i < custmrOrder.size(); i++) {
                            totalOrder += custmrOrder.get(i).priceItem * custmrOrder.get(i).quantityItem;
                        }
                        System.out.println("Total:\t" + totalOrder);

                        // enclose to a do-while + if-else for polishing
                        System.out.print("Payment Amount: ");
                        int amtPaid = inputUser.nextInt();
                        int change = amtPaid - totalOrder;
                        System.out.println("Customer's Change: " + change + " Php");
                        custmrOrder.clear();
                    }
                    else {
                        System.out.println("\nOrder was not finalized.");
                    }

                }

                // Void an Order
                case "3" -> {
                    System.out.println("\n\nCurrent Order:");
                    for (int i = 0; i < custmrOrder.size(); i++) {
                        System.out.println(custmrOrder.get(i).quantityItem + "x\t\t" + custmrOrder.get(i).nameItem + "\t\t\t\t" + (custmrOrder.get(i).priceItem * custmrOrder.get(i).quantityItem) + " Php");
                    }

                    System.out.print("Select an order to remove: ");
                    int removeOrder = inputUser.nextInt();
                    if (removeOrder != 0) {
                        removeOrder--;
                    }

                    custmrOrder.remove(removeOrder);
                    System.out.println("\nOrder number " + (removeOrder+1) + " was removed.");
                }

                // Cancel the whole order
                case "4" -> {
                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Are you sure you want to clear out the order? ");
                    if (inputUser.nextInt() == 1) {
                        custmrOrder.clear();
                        System.out.println("\nOrder list is now cleared.");
                    }
                    else {
                        System.out.println("\nOrder list was not cleared.");
                    }

                }

                // Add a menu item
                case "5" -> {

                    System.out.println("\n\n1 = YES | 0 = NO");
                    System.out.print("Do you want to add another item to the menu? ");
                    int choice5 = inputUser.nextInt();
                    inputUser.nextLine();
                    if (choice5 == 1) {
                        System.out.print("What should be the item/s name? ");
                        String name = inputUser.nextLine();
                        System.out.print("How many is it in ONE ORDER? ");
                        int quantity = inputUser.nextInt();
                        inputUser.nextLine();
                        System.out.print("What is the item/s price? ");
                        int price = inputUser.nextInt();
                        inputUser.nextLine();


                        menuList.add(menuList.size(), new Core.infoFood(quantity, name, price));
                        System.out.println("Item was added to the menu.");

                    }
                    else {
                        System.out.println("Adding process was canceled");
                    }
                }

                case "6" -> {
                    break mm;
                }

                default -> {
                    System.out.println("Invalid Input!");
                }
            }
        }
        while (!condition);

    }
}
