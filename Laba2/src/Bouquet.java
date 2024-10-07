import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers;

    public Bouquet() {
        this.flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public double getTotalPrice() {
        return flowers.stream().mapToDouble(Flower::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Bouquet: " + flowers;
    }
}
