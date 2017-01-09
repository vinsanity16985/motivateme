package apps.motivateme;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    private static final String TAG = "AlarmFragment";
    private static final int NOON = 12;

    private SharedPreferences myPrefs;

    private TextView bottomText;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private ToggleButton toggleButton;
    private Button createButton;

    private Calendar calendar;
    private int hour;
    private int minute;
    private boolean tod;
    private String bottom;

    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        myPrefs = getContext().getSharedPreferences("MotivateMePrefs", Context.MODE_PRIVATE);

        bottomText = (TextView)view.findViewById(R.id.bottom_text);
        hourPicker = (NumberPicker)view.findViewById(R.id.hour_picker);
        minutePicker = (NumberPicker)view.findViewById(R.id.minute_picker);
        toggleButton = (ToggleButton)view.findViewById(R.id.toggle_button);
        createButton = (Button)view.findViewById(R.id.create_button);

        calendar = Calendar.getInstance();
        hour = getHour();
        minute = calendar.getTime().getMinutes();
        tod = toggleButton.isChecked();
        bottom = "Alarm Set for " + hour + ":" + minute;
        setHourPicker(hourPicker);
        setMinutePicker(minutePicker);

        updateText();

        //ToggleButton onClickListener
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tod = toggleButton.isChecked();
                updateText();
            }
        });

        //Button onClickListener
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.putBoolean("tod", tod);
                editor.putBoolean("alarm set", true);
                editor.commit();

                AlarmSetFragment fragment = new AlarmSetFragment();
                FragmentManager fManager = getFragmentManager();
                fManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        return view;
    }

    //Returns properly formatted hour
    private int getHour(){
        int pHour = calendar.getTime().getHours(); //this returns military time ex. 5:00 pm = 17:00
        if(pHour > NOON){
            return pHour - 12;
        }
        else{
            return pHour;
        }
    }

    private void updateText() {
        if(tod){
            bottom = "Alarm set for " + hour + ":" + String.format("%02d", minute) + " am";
        }
        else{
            bottom = "Alarm set for " + hour + ":" + String.format("%02d", minute) + " pm";
        }
        bottomText.setText(bottom);
    }

    //Set up the hour NumberPicker
    private void setHourPicker(NumberPicker np){
        np.setMinValue(1);
        np.setMaxValue(12);
        np.setWrapSelectorWheel(true);
        np.setValue(hour);
        np.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });

        //Listener for hourPicker
        hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                hour = newVal;
                updateText();
            }
        });
    }

    //Set up the minute NumberPicker
    private void setMinutePicker(NumberPicker np){
        np.setMinValue(0);
        np.setMaxValue(59);
        np.setWrapSelectorWheel(true);
        np.setValue(minute);
        np.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });

        //Listener for minutePicker
        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                minute = newVal;
                updateText();
            }
        });
    }

}
