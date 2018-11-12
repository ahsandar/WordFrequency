import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Utility {

    public static String dateStampYesterday() {
        Date yesterday = dateValue(-1);
        return hashKeyDateFormat().format(yesterday).toString();
    }

    private static Date dateValue(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static SimpleDateFormat hashKeyDateFormat() {
        return  new SimpleDateFormat("yyyyMMdd");
    }
}
