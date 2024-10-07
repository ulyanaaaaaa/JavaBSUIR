public class Stock {
    private String name;
    private Float price;
    private boolean isAvailable;

    public Stock(String name, Float price, boolean isAvailable){
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Stock(){
        name = "No";
        price = 0f;
        isAvailable = false;
    }

    public String getName() {
        return name;
}

    public boolean getAvailable() {
        return isAvailable;
    }

    public Float getPrice() {
        return price;
    }

    public void Print() {
        System.out.println("Название: " + name);
        if(isAvailable){
            System.out.println("Есть в наличии.");
        }else{
            System.out.println("Нет в наличии.");
        }
        System.out.println("Стоимость: " + price);
    }
}
