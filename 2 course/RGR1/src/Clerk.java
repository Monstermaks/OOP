import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Clerk implements Callable<String> {

    private Box box;
    private String name;

    public Clerk(Box box, String name) {
        this.box = box;
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        Thread.currentThread().setName(name);
        int form_count = 0;
        while (true) {
            if (box.isFull()) break;
            if (box.isFree()) {
                box.setFree(false);
                box.addForm();
                form_count++;
                box.setFree(true);
            }
            sleep((int)(200 * Math.random()));
        }
        return Thread.currentThread().getName() + " add " + form_count + " forms";
    }
}
