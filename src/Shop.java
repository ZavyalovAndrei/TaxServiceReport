import java.util.concurrent.atomic.LongAdder;

public class Shop extends Thread {
    private static final int DELAY = 1;
    int[] SALES_REPORT;
    LongAdder TOTAL_REPORT;


    public Shop(ThreadGroup group, String name, int[] salesReport, LongAdder totalReport) {
        super(group, name);
        this.SALES_REPORT = salesReport;
        this.TOTAL_REPORT = totalReport;
    }

    @Override
    public void run() {
        System.out.println(getName() + " запускает передачу налогового отчета.");
        try {
            for (int j : SALES_REPORT) {
                Thread.sleep(DELAY);
                TOTAL_REPORT.add(j);
            }
        } catch (InterruptedException err) {
            return;
        } finally {
            System.out.println(getName() + " завершил передачу налогового отчета.");
            interrupt();
        }
    }
}