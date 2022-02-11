package utils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Time converter class is used to transform times retrieved from the database and sent to the database
 */
public class timeConverter {

    ZoneId currentZone = ZoneId.systemDefault();

        //CONVERT TIME FROM UTC TO LOCAL TIME WHEN PULLING FROM THE DATABASE
        //CONVERT TIME FROM LOCAL TIME TO UTC WHEN STORING IN THE DATABASE

        //PST -8 FROM UTC
        //MST -7 FROM UTC
        //CDT -6 FROM UTC
        //EST -5 FROM UTC

    /**
     * toUTC is used to transform time entered to be stored as UTC time in the database
     * @param dateTime
     * @return
     */
    public static String toUTC(String dateTime) {
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();
        String finishUTC =ldtIn.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
        return finishUTC;
    }

    /**
     * toLocal is used to transform the time entered to be presented in the program as the users local time
     * @param dateTime
     * @return
     */
    public static String toLocal(String dateTime) {
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime zdtOut = ldt.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldOutFinal = zdtOutToLocalTZ.toLocalDateTime();
        String finishLocal =ldOutFinal.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
        return finishLocal;
    }

    /**
     * setStartHours is used to transform the business start hours based on the users local time
     * @return
     */
    public static int setStartHours(){

        int bizStartUTC = 8;

        TimeZone tz = TimeZone.getDefault();
        Calendar calendar = GregorianCalendar.getInstance(tz);
        int offset = tz.getOffset(calendar.getTimeInMillis());
        if (offset != 0) {
            int hours = Math.abs((offset / (60 * 1000)) / 60) - 5;
            int bizStartLocal = bizStartUTC - hours;
            return bizStartLocal;
        }else{
            return bizStartUTC;
        }

    }

    /**
     * setEndHours is used to transform the business end hours based on the users local time
     * @return
     */
    public static int setEndHours() {

        int bizStartUTC = 22;

        TimeZone tz = TimeZone.getDefault();
        Calendar calendar = GregorianCalendar.getInstance(tz);
        int offset = tz.getOffset(calendar.getTimeInMillis());
        if (offset != 0) {
            int hours = Math.abs((offset / (60 * 1000)) / 60) - 5;
            int bizStartLocal = bizStartUTC - hours;
            return bizStartLocal;
        } else {
            return bizStartUTC;
        }

    }

    }

