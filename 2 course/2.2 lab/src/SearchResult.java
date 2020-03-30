import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class SearchResult implements Runnable {

    private String filename, find;
    private Exchanger<String> ex;

    public SearchResult(String filename, String find, Exchanger<String> ex) {
        this.filename = filename;
        this.find = find;
        this.ex = ex;
    }

    @Override
    public void run() {
        int inputIndex = 0;
        try (var sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                if (sc.nextLine().toLowerCase().contains(find.toLowerCase())) {
                    inputIndex++;
                }
            }
            System.out.println(ex.exchange(find + " contains in file " + filename + " " + inputIndex + " times"));
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
