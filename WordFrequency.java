
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WordFrequency implements Runnable {
    /**
     *
     */

    private String line = null;
    private String dateStamp = null;

    public WordFrequency(String line, String dateStamp) {
        this.line = line;
        this.dateStamp = dateStamp;
    }

    public void run() {
        try {
            countWords().forEach((word, count) -> {
                String wordKey = word.toLowerCase().replaceAll("\\s","");
                WordCount.addCountForTheDay(dateStamp, wordKey, count);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Long> countWords() {

        Map<String, Long> counterMap = new HashMap<>();
        String[] splited = line.split(",");
        String[] splitedArray = Arrays.copyOfRange(splited, 1, splited.length);
        Stream.of(splitedArray).collect(Collectors.groupingBy(k -> k, () -> counterMap, Collectors.counting()));
        return counterMap;
    }

}
