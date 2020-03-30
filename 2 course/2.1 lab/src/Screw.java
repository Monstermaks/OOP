import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

//винтик
public class Screw implements Runnable {
    private int num;
    private CyclicBarrier cb;

    public Screw(int num, CyclicBarrier cb) {
        this.cb = cb;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
            System.out.println(num + " screw completed!");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
