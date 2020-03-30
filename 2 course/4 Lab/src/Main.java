import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[5000];
        for (int i = 0; i < 5000; i++) {
            arr[i] = (int) (Math.random() * 5000 - Math.random() / 125);
        }

        Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());

        Runnable Action = () -> System.out.println(syncCollection.stream().max(Integer::compareTo));

        CyclicBarrier cb = new CyclicBarrier(10, Action);
        for (int i = 0; i < 10; i++) {
            new NewThread(cb, syncCollection, arr, i * 500, i * 500 + 500).start();
        }
    }
}