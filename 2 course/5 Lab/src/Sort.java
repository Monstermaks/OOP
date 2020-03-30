import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Sort<T extends Number & Comparable> extends Thread {

    private T matrix[][];
    private int column, lines;
    private  CyclicBarrier cb;

    public Sort(T matrix[][], int column, int lines, CyclicBarrier cb) {
        this.matrix = matrix;
        this.column = column;
        this.lines = lines;
        this.cb = cb;
    }

    @Override
    public void run() {
        T x;
        for (int i = 0; i < lines; i++) {
            for (int j = lines - 1; j > i; j--) {
                if (matrix[j - 1][column].compareTo(matrix[j][column]) > 0) {
                    x = matrix[j - 1][column];
                    matrix[j - 1][column] = matrix[j][column];
                    matrix[j][column] = x;
                }
            }
        }
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
