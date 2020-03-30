import java.util.concurrent.Exchanger;

public class Search extends Thread {

    private String[] files;
    private String find;

    public Search(String[] files, String find) {
        this.find = find;
        this.files = files;
    }

    @Override
    public void run() {
        var ex = new Exchanger<String>();
        for (String f : files) {
            new Thread(new SearchResult(f, find, ex)).start();
        }
        try {
            for (String f : files) {
                System.out.println(ex.exchange(""));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
