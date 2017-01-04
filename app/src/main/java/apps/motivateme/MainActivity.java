package apps.motivateme;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity{

    private static final String TAG = "MainActivity";

    private static ArrayList<Alarm> alarmList;
    private static ListAdapter adapter;
    private static Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = savedInstanceState;

        if(findViewById(R.id.fragment_container) != null){
            if(data != null) {
                //Show alarm set fragment
                AlarmSetFragment alarmSetFragment = new AlarmSetFragment();
                alarmSetFragment.setArguments(data);
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
