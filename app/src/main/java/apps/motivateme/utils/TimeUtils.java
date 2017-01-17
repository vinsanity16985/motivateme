package apps.motivateme.utils;

import java.util.Calendar;

/**
 * Created by vinsa_000 on 1/9/2017.
 */
public class TimeUtils {
    private static Calendar calendar;

    private TimeUtils(){}

    public static String getTime(){
        calendar = Calendar.getInstance();
        String time;
        int hour = getHour();
        int minute = calendar.getTime().getMinutes();
        boolean tod = calendar.getTime().getHours() >= 12 ? false: true;

        if(tod){
            time = hour + ":" + String.format("%02d", minute) + "am";
        }
        else{
            time = hour + ":" + String.format("%02d", minute) + "pm";
        }

        return time;
    }

    //Returns properly formatted hour
    private static int getHour(){
        int pHour = calendar.getTime().getHours();
        if(pHour > 12){
            return pHour - 12;
        }
        else{
            return pHour;
        }
    }
}
