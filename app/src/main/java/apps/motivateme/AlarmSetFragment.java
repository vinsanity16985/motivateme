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


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmSetFragment extends Fragment {

    private SharedPreferences myPrefs;

    private Button deleteButton;

    public AlarmSetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_set, container, false);
        myPrefs = getContext().getSharedPreferences("MotivateMePrefs", Context.MODE_PRIVATE);

        deleteButton = (Button)view.findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putBoolean("alarm set", false);
                editor.commit();

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
