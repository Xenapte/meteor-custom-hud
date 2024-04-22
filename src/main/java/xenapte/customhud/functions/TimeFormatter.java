package xenapte.customhud.functions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {
    public static String format(String fmt) {
        try {
            var formatter = new SimpleDateFormat(fmt);
            return formatter.format(new Date());
        }
        catch (IllegalArgumentException e) {
            return e.toString();
        }
    }
}
