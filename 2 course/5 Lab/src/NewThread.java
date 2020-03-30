import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NewThread extends Thread {

    private int line, columns;
    private CyclicBarrier cb;

    public NewThread(int line, int columns, CyclicBarrier cb) {
        this.line = line;
        this.columns = columns;
        this.cb = cb;
    }

    @Override
    public void run(){
        for (int i = 0; i < columns; i++) {
            Main.setMatrixEl(Math.round(Math.random() * 10 * 1000.0) / 1000.0, line, i);
        }
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
