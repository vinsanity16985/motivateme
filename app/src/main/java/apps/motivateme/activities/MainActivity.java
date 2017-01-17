package apps.motivateme.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.Calendar;

import apps.motivateme.interfaces.IntentInterface;
import apps.motivateme.R;
import apps.motivateme.fragments.AlarmFragment;
import apps.motivateme.receivers.AlarmReceiver;
import apps.motivateme.fragments.AlarmSetFragment;

public class MainActivity extends FragmentActivity implements IntentInterface {

    private static final String TAG = "MainActivity";

    private Context context;
    private SharedPreferences myPrefs;
    private AlarmManager alarmMgr;
    private FragmentManager fMgr;
    private Intent brIntent;
    private PendingIntent brPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        myPrefs = getSharedPreferences("MotivateMePrefs", Context.MODE_PRIVATE);
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        fMgr = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if(myPrefs.getBoolean("alarm set", true)) {
                //Show alarm set fragment
                AlarmSetFragment alarmSetFragment = new AlarmSetFragment();
                alarmSetFragment.setArguments(savedInstanceState);

                setInitialFragment(alarmSetFragment);
            }
            else{
                //Show alarm creator
                AlarmFragment alarmFragment = new AlarmFragment();

                setInitialFragment(alarmFragment);
            }
        }
    }


    @Override
    public void setAlarm(Calendar calendar) {
        AlarmSetFragment newFragment = new AlarmSetFragment();
        brIntent = new Intent(context, AlarmReceiver.class);
        brPendingIntent = PendingIntent.getBroadcast(context, 0, brIntent, 0);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, brPendingIntent);

        switchFragments(newFragment);
    }

    @Override
    public void cancelAlarm(){
        AlarmFragment newFragment = new AlarmFragment();    //Fragment to switch to

        alarmMgr.cancel(brPendingIntent);                   //cancel the PendingIntent
        switchFragments(newFragment);                       //switch fragments
    }

    private void setInitialFragment(Fragment frag){
        fMgr.beginTransaction().add(R.id.fragment_container, frag).commit();
    }

    private void switchFragments(Fragment frag){
        fMgr.beginTransaction().replace(R.id.fragment_container, frag).commit();
    }
}
