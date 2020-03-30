import java.util.concurrent.Callable;

public class Average<T extends Number> implements Callable<String> {

    private T matrix[][];
    private int line, columns;

    public Average(T matrix[][], int line, int columns) {
        this.matrix = matrix;
        this.line = line;
        this.columns = columns;
    }

    @Override
    public String call() {
        Double summ = 0.;
        for(T i : matrix[line]){
            summ += i.doubleValue();
        }
        return "Average in line " + (line + 1) + " = " + summ / columns;
    }
}
