public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Fasteners(i + 1)).start();
        }
    }
}
