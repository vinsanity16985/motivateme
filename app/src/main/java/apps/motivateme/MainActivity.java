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

        if(data == null){
            data = new Bundle();
        }


        if(findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null) {
                return;
            }
            if(data.getBoolean("Alarm Set", false)) {
                //Show list of alarms
            }
            else{
                //Show alarm creator
                AlarmFragment alarmFragment = new AlarmFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, alarmFragment).commit();
            }
        }


//        alarmList = new ArrayList<>();
//
//        int hour = 10;
//        int minute = 0;
//        Alarm alarm = new Alarm(hour, minute);
//
//        alarmList.add(alarm);
//
//        adapter = new MyAdapter(this,R.layout.row_layout, alarmList);
//
//        setListAdapter(adapter);
//        Log.i(TAG, "Set list adapter");
    }
}
