import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {
    public static void main(String[] args) {
        var box = new Box();
        var ex = newFixedThreadPool(3);
        Future<String> cl1 = ex.submit(new Clerk(box, "Clerk 1"));
        Future<String> cl2 = ex.submit(new Clerk(box, "Clerk 2"));
        Future<String> cl3 = ex.submit(new Clerk(box, "Clerk 3"));
        var calcs = new ArrayList<Future<String>>() {{
            add(cl1);
            add(cl2);
            add(cl3);
        }};
        calcs.stream().forEach(c -> {
            try {
                System.out.println(c.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                ex.shutdown();
            }
        });
        System.out.println(box.getForm_count());
    }
}
