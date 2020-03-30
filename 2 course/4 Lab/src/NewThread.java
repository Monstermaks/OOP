import java.util.Collection;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NewThread<T extends Number & Comparable> extends Thread {

    private CyclicBarrier bar;
    private Collection<T> sColl;
    private T[] arr;
    private int from, to;

    public NewThread(CyclicBarrier bar, Collection<T> sColl, T[] arr, int from, int to) {
        this.sColl = sColl;
        this.bar = bar;
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        T max = arr[from];
        for (int i = from; i < to; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        sColl.add(max);
        try {
            bar.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
