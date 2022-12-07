public class Core {
    static class infoFood {
        int quantityItem, priceItem;
        String nameItem;

        infoFood(int quantityItem, String nameItem, int priceItem) {
            this.quantityItem = quantityItem;
            this.nameItem = nameItem;
            this.priceItem = priceItem;
        }
    }

    static void printList(int size, int quantity, String name, int price) {
        System.out.printf("x%-15s %-30s %s\n" , quantity, name, (price*quantity));
    }

}
