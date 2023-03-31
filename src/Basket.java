import java.io.*;

public class Basket {
    private final String[] products;
    private final int[] prices;
    private final int[] count;

    private int sumProducts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.count = new int[products.length];
    }
    public Basket(String[] products, int[] prices, int[] count, int sumProducts) {
        this.products = products;
        this.prices = prices;
        this.count = count;
        this.sumProducts = sumProducts;
    }
    public void printProducts() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб/шт");
        }
    }
    public void addToCard(int product, int amount) {
        int currentPrice = prices[product];
        sumProducts += amount * currentPrice;
        count[product] += amount;
    }
    public void printCart() {
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println(
                        products[i] + " " + count[i] + " шт " + prices[i] + " руб/шт " + (count[i] * prices[i]) + " руб в сумме");
            }
        }
        System.out.println("Итого: " + this.sumProducts + " руб");
    }
    public void saveTxt(File textFile) throws Exception {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int i = 0; i < products.length; i++) {
                if (i != (products.length - 1)) {
                    out.print(products[i] + " ");
                } else {
                    out.println(products[i]);
                }
            }
            for (int j = 0; j < prices.length; j++) {
                if (j != (prices.length - 1)) {
                    out.print(prices[j] + " ");
                } else {
                    out.println(prices[j]);
                }
            }
            for (int c = 0; c < count.length; c++) {
                if (c != (count.length - 1)) {
                    out.print(count[c] + " ");
                } else {
                    out.println(count[c]);
                }
            }
            out.println(sumProducts);
        }
    }
    public static Basket loadFromTxtFile(File textFile) throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(textFile))) {
            String[] product = in.readLine().split(" ");
            String[] pricesString = in.readLine().split(" ");
            int[] prices = new int[pricesString.length];
            for (int i = 0; i < pricesString.length; i++) {
                prices[i] = Integer.parseInt(pricesString[i]);
            }
            String[] countString = in.readLine().split(" ");
            int[] count = new int[countString.length];
            for (int j = 0; j < countString.length; j++) {
                count[j] = Integer.parseInt(countString[j]);
            }
            int sumProducts = Integer.parseInt(in.readLine());
            return new Basket(product, prices, count, sumProducts);
        }
    }
}