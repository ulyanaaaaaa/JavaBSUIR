public class WriterInfo {
    public void Print(Stock stock){
        System.out.println("Название: " + stock.getName());
        if(stock.getAvailable()){
            System.out.println("Есть в наличии.");
        }else{
            System.out.println("Нет в наличии.");
        }
        System.out.println("Стоимость: " + stock.getPrice());
    }
}
