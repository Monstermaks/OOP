import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    private static Double[][] matrix;

    public static void setMatrixEl(Double el, int line, int column) {
        matrix[line][column] = el;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter number of lines in matrix:");
            int lines = in.nextInt();
            System.out.println("Enter number of columns in matrix:");
            int columns = in.nextInt();
            matrix = new Double[lines][columns];

            Runnable Action = () -> {
                for (int i = 0; i < lines; i++) {
                    for (int j = 0; j < columns; j++) {
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.println();
                }
            };

            CyclicBarrier cb = new CyclicBarrier(lines, Action);
            for (int i = 0; i < lines; i++) {
                new NewThread(i, columns, cb).start();
            }

            ExecutorService ex = newFixedThreadPool(lines);
            var calcs = new ArrayList<Future<String>>(){{
                for (int i = 0; i < lines; i++) {
                    add(ex.submit(new Average(matrix, i, columns)));
                }
            }};
            calcs.stream().forEach(c  -> {
                try {
                    System.out.println(c.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            ex.shutdown();

            cb = new CyclicBarrier(columns, Action);
            for (int i = 0; i < columns; i++) {
                new Sort(matrix, i, lines, cb).start();
            }

        } catch (InputMismatchException e) {
            System.err.println("You entered not a number!");
        }
    }
}
