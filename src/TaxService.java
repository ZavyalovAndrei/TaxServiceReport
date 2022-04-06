import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

public class TaxService extends Thread {
    LongAdder TOTAL_REPORT;
    ThreadGroup SHOPS;
    private static final int CHECK_END_REPORTS_DELAY = 100;

    public TaxService(LongAdder totalReport, ThreadGroup shops) {
        this.TOTAL_REPORT = totalReport;
        this.SHOPS = shops;
    }

    @Override
    public void run() {
        System.out.println("Портал налоговой службы: \"Готов к принятию отчета.\"");
        try {
            while (SHOPS.activeCount() > 0) {
                Thread.sleep(CHECK_END_REPORTS_DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Портал налоговой службы: \"Отчет принят.\"");
            System.out.format("Сумма продаж на %tF", new Date());
            System.out.println(" : " + TOTAL_REPORT + " рублей.");
            interrupt();
        }
    }
}