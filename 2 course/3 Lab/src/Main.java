import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String []args){
        Integer arr[] = new Integer[10];
        for(int i = 0; i < 10; i++){
            arr[i] = i + 1;
        }

        ExecutorService ex = newFixedThreadPool(2);
        var calcs = new ArrayList<Future<String>>(){{
            add(ex.submit(new NewThread(arr, true)));
            add(ex.submit(new NewThread(arr, false)));
        }};
        calcs.stream().forEach(c->{
            try {
                System.out.println(c.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        ex.shutdown();
    }
}