import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

public class MultiLineProcessor {

    private static int POOL_SIZE = 5;

    public void processWordFrequency(String[] lines, String dateStamp) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                WordFrequency line = new WordFrequency(lines[i], dateStamp);
                executor.execute(line);
            }
        }
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}