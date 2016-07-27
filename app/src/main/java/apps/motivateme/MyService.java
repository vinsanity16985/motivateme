package apps.motivateme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vinsa_000 on 7/26/2016.
 */
public class MyService extends Service {
    MyReceiver myReceiver = new MyReceiver();
    public void onCreate(){
        //Calls the onCreate() method from Service
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        setAlarm();
        //If the app is killed during this onStartCommand()
        // and there are no pending intents then kill this service
        // and don't restart it until explicitly called from somewhere else
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setAlarm(){
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //Change the context back to this if MainActivity() doesn't work
        Intent intent = new Intent(new MainActivity(), MyReceiver.class);

        //If the PendingIntent already exists then keep it but send new extra data for it
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //Using testing values, will be set to 9:00 AM
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 17);

        //Going to wake up the phone
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 86400000, pendingIntent);
    }
}