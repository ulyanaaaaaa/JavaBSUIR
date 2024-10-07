
public class Main {
    public static void main(String[] args) {
        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose("Red Rose", 2.5));
        bouquet.addFlower(new Tulip("Yellow Tulip", 1.5));

        System.out.println(bouquet);
        System.out.println("Total Price: " + bouquet.getTotalPrice());
        }
    }