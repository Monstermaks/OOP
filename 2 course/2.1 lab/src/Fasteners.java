import java.util.concurrent.CyclicBarrier;

//крепёж
public class Fasteners implements Runnable {
    private int num;

    public Fasteners(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        Runnable Action = () -> {
            System.out.println("Producing of " + num + " fastener completed!");
        };
        CyclicBarrier cb = new CyclicBarrier(3, Action);
        new Thread(new Screw(num, cb)).start();
        new Thread(new ScrewNut(num, cb)).start();
        new Thread(new Shim(num, cb)).start();
    }
}