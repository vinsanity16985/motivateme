package apps.motivateme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends FragmentActivity implements IntentInterface{

    private static final String TAG = "MainActivity";

    private Context context;
    private SharedPreferences myPrefs;
    private AlarmManager alarmMgr;
    private Intent intent;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        myPrefs = getSharedPreferences("MotivateMePrefs", Context.MODE_PRIVATE);

        if(findViewById(R.id.fragment_container) != null){
            if(myPrefs.getBoolean("alarm set", true)) {
                //Show alarm set fragment
                AlarmSetFragment alarmSetFragment = new AlarmSetFragment();
                alarmSetFragment.setArguments(savedInstanceState);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, alarmSetFragment).commit();
            }
            else{
                //Show alarm creator
                AlarmFragment alarmFragment = new AlarmFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, alarmFragment).commit();
            }
        }
    }

    @Override
    public void setAlarm(Calendar calendar) {
        intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    @Override
    public void cancelAlarm(){
        alarmMgr.cancel(alarmIntent);
    }
}
