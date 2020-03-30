import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

//гайка
public class ScrewNut implements Runnable {
    private int num;
    private CyclicBarrier cb;

    public ScrewNut(int num, CyclicBarrier cb) {
        this.cb = cb;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            sleep(3000);
            System.out.println(num + " screw-nut completed!");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
