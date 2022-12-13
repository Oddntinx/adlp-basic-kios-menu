/*
Program: The really basic restaurant kiosk menu's function class
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/13/2022
Version: 1.03
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class _function {
    static void orderProcess(int indexItem, int quantityFood) {
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

    static void quantityCycle() {
        for (int i = 0; i < Main.menuList.size(); i++) {
            Main.menuList.get(i).quantityItem = 1;
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
