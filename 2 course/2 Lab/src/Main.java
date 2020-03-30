import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) {
        Double[] arr = new Double[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = Double.valueOf(i);
        }

        ExecutorService ex = newFixedThreadPool(2);
        var calcs = new ArrayList<Future<Double>>() {{
            add(ex.submit(new NewThread(arr, true)));
            add(ex.submit(new NewThread(arr, false)));
        }};
        calcs.stream().forEach(c -> {
            try {
                System.out.println(c.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ex.shutdown();
    }
}
