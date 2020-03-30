import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Clerk implements Callable<String> {

    private Box box;
    private int form_count;
    private String name;

    public Clerk(Box box, String name) {
        this.box = box;
        form_count = 0;
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        Thread.currentThread().setName(name);
        while (!box.isFull()) {
            if (box.isFree()) {
                box.setFree(false);
                box.addForm();
                form_count++;
                box.setFree(true);
            }
            sleep(100);
        }
        return Thread.currentThread().getName() + " add " + form_count + " forms";
    }
}
