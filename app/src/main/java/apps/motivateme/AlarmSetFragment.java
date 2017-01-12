package apps.motivateme;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmSetFragment extends Fragment {

    private static final int NOT_FOUND = -1;

    private SharedPreferences myPrefs;
    private IntentInterface listener;
    private Context context;

    private int hour;
    private int minute;
    private boolean tod;
    private String cTime;
    private String aTime;

    private TextView currentTime;
    private TextView alarmTime;
    private Button deleteButton;

    public AlarmSetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context c){
        super.onAttach(c);
        if(c instanceof IntentInterface){
            listener = (IntentInterface)c;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_set, container, false);

        context = getContext();
        myPrefs = context.getSharedPreferences("MotivateMePrefs", Context.MODE_PRIVATE);

        deleteButton = (Button)view.findViewById(R.id.delete_button);
        currentTime = (TextView)view.findViewById(R.id.textview_current);
        alarmTime = (TextView)view.findViewById(R.id.textview_alarm);

        hour = myPrefs.getInt("hour", NOT_FOUND);
        minute = myPrefs.getInt("minute", NOT_FOUND);
        tod = myPrefs.getBoolean("tod", false);

        cTime = CurrentTime.getTime();
        if(tod){
            aTime = "Alarm Set for " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + "am";
        }
        else{
            aTime = "Alarm Set for " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + "pm";
        }

        currentTime.setText(cTime);
        alarmTime.setText(aTime);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Erase Alarm Data in SharedPreferences
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putBoolean("alarm set", false);
                editor.commit();

                //Cancel the alarm
                listener.cancelAlarm();

                //Switch back to AlarmFragment
                AlarmFragment fragment = new AlarmFragment();
                FragmentManager fManager = getFragmentManager();
                fManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        return view;
    }

}
