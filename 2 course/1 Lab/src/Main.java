import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }

        new Thread(
                () -> Arrays.stream(arr).filter(x -> x % 2 != 0).forEach(i -> System.out.println(Thread.currentThread().getName() + " " + i))
        ).start();
        new Thread(
                () -> Arrays.stream(arr).filter(x -> x % 2 == 0).forEach(i -> System.out.println(Thread.currentThread().getName() + " " + i))
        ).start();
    }
}
