import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

//шайба
public class Shim implements Runnable {
    private int num;
    private CyclicBarrier cb;

    public Shim(int num, CyclicBarrier cb) {
        this.cb = cb;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
            System.out.println(num + " shim completed!");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
