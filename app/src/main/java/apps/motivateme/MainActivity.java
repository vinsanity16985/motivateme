package apps.motivateme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity{

    private static final String TAG = "MainActivity";

    private SharedPreferences myPrefs;

    private static ArrayList<Alarm> alarmList;
    private static ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
