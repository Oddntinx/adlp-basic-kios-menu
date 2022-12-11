import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Program: The really basic restaurant kiosk menu's core class
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/07/2022
Version: 1.01
 */
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

    static void printMenuList(int index, String name, int price) {
        System.out.printf("%-12s %-35s %-1s\n", (index+1), name, price + " Php");
    }
    static void printOrderList(int index, int quantity, String name, int price) {
        System.out.printf("%-12s x%-17s %-35s %-1s\n", (index+1), quantity, name, (price*quantity) + " Php");
    }

    static void bannerMenuList() {
        System.out.printf("%-17s %-31s %s\n", "Item No.", "Name", "Price");
    }
    static void bannerOrderList() {
        System.out.printf("%-10s %-25s %-31s %s\n", "Item No.", "Quantity", "Name", "Price");
    }

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
