import java.util.Arrays;
import java.util.concurrent.Callable;

import static java.util.Comparator.naturalOrder;

public class NewThread<T extends Number & Comparable> implements Callable<String> {

    private boolean type;
    private T[] arr;

    public NewThread(T[] arr, boolean type) {
        this.type = type;
        this.arr = arr;
    }

    @Override
    public String call() {
        String value;
        if (type) {
            value = "Maximum of this array is: " + Arrays.stream(arr).max(naturalOrder()).get();
        } else {
            value = "Minimum of this array is: " + Arrays.stream(arr).min(naturalOrder()).get();
        }
        return value;
    }
}
