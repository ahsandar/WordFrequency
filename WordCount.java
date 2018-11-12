import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordCount {

    public static Map<String, Map<String, Long>> totalDayCount = new ConcurrentHashMap<>();

    public static void addCount(String dateStamp, String word, Long count) {
        Map<String, Long> totalCount = totalDayCount.get(dateStamp);
        totalCount.put(word, getCount(dateStamp, word) + count);
    }

    public static Long getCount(String dateStamp, String word) {
        Long count = 0L;
        if (totalDayCount.containsKey(dateStamp)){
            Map<String, Long> totalCount = totalDayCount.get(dateStamp);
            count = (totalCount.containsKey(word) ? totalCount.get(word).longValue() : 0);
        }
         return count;
    }

    public static void addCountForTheDay(String dateStamp, String word, Long count) {
        if (!totalDayCount.containsKey(dateStamp)) {
            totalDayCount.put(dateStamp,  new ConcurrentHashMap<>());
        }
           addCount(dateStamp, word, count);

    }

}