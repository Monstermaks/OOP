import java.util.concurrent.Callable;

public class NewThread<T extends Number> implements Callable<String> {

    private T arr[];
    private boolean type;

    public NewThread(T arr[], boolean type) {
        this.type = type;
        this.arr = arr;
    }

    @Override
    public String call() {
        String value;
        if (type) {
            Double sum = 0.;
            for (T i : arr) {
                sum += i.doubleValue();
            }
            value = "Sum: " + sum.toString();
        } else {
            Double mult = 1.;
            for (T i : arr) {
                mult *= i.doubleValue();
            }
            value = "Multiply: " + mult.toString();
        }
        return value;
    }
}
