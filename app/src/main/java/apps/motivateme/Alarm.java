package apps.motivateme;

import java.util.Date;

/**
 * Created by vinsa_000 on 12/31/2016.
 */
public class Alarm {
    private Date time;

    public Alarm(int hour, int minute){
        time = new Date();
        time.setHours(hour);
        time.setMinutes(minute);
    }

    public Date getTime(){return this.time;}
    public void setHour(int hour){this.time.setHours(hour);}
    public void setMinute(int minute){this.time.setMinutes(minute);}
}
