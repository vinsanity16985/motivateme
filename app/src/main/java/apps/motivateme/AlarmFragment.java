package apps.motivateme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    private static final String TAG = "AlarmFragment";

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

        bottomText = (TextView)view.findViewById(R.id.bottom_text);
        hourPicker = (NumberPicker)view.findViewById(R.id.hour_picker);
        minutePicker = (NumberPicker)view.findViewById(R.id.minute_picker);
        toggleButton = (ToggleButton)view.findViewById(R.id.toggle_button);
        createButton = (Button)view.findViewById(R.id.create_button);

        calendar = Calendar.getInstance();
        hour = getHour();
        minute = calendar.getTime().getMinutes();
        tod = true;
        bottom = "Alarm Set for " + hour + ":" + minute;
        setHourPicker(hourPicker);
        setMinutePicker(minutePicker);

        bottomText.setText(bottom);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("hour", hour);
                bundle.putInt("minute", minute);
                bundle.putBoolean("tod", tod);

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        return view;
    }

    //Returns properly formatted hour
    private int getHour(){
        int pHour = calendar.getTime().getHours();
        if(pHour > 12){
            return pHour - 12;
        }
        else{
            return pHour;
        }
    }

    private void updateText() {
        bottom = "Alarm set for " + hour + ":" + minute;
        bottomText.setText(bottom);
    }

    //Set up the hour NumberPicker
    private void setHourPicker(NumberPicker np){
        np.setMinValue(1);
        np.setMaxValue(12);
        np.setWrapSelectorWheel(true);
        np.setValue(hour);

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
