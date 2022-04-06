import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    private static final int MIN_COST = 100;
    private static final int MAX_COST = 8000;
    private static final int PURCHASE_QUANTITY = 3000;
    private static final int SHOP_QUANTITY = 3;

    public static void main(String[] args) {
        ThreadGroup shops = new ThreadGroup("Shops");
        Collection<Thread> threads = new ArrayList<>(SHOP_QUANTITY);
        LongAdder totalReport = new LongAdder();

        for (int i = 0; i < SHOP_QUANTITY; i++) {
            threads.add(new Shop(shops, "Магазин №" + (i + 1), saleReport(), totalReport));
        }
        new TaxService(totalReport, shops).start();
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static int[] saleReport() {
        Random random = new Random();
        int[] salesArray = new int[PURCHASE_QUANTITY];
        for (int i = 0; i < PURCHASE_QUANTITY; i++) {
            salesArray[i] = random.nextInt(MIN_COST, MAX_COST);
        }
        return salesArray;
    }
}