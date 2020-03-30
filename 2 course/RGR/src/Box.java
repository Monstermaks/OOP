import java.util.concurrent.atomic.AtomicInteger;

public class Box {

    private int form_max;
    private AtomicInteger form_count;
    private boolean free;

    public Box() {
        form_max = 100;
        form_count = new AtomicInteger(0);
        free = true;
    }

    public synchronized boolean isFull() {
        if (form_count.get() >= form_max) {
            return true;
        } else return false;
    }

    public synchronized void addForm() {
        form_count.incrementAndGet();
    }

    public int getForm_count() {
        return form_count.get();
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
