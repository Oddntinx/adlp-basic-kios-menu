/*
Program: The really basic restaurant kiosk menu's core class
Creation Date: 12/06/2022
Declared Finished Date: 12/07/2022
Last Modified: 12/07/2022
Version: 1.0
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
}
