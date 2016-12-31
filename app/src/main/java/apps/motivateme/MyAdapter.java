package apps.motivateme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vinsa_000 on 12/31/2016.
 */
public class MyAdapter extends ArrayAdapter<Alarm> {
    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context, int resource, List<Alarm> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_layout, null);
            Log.i(TAG, "Inflated ConvertView");
        }

        Alarm alarm = getItem(position);

        if(alarm != null){
            TextView text = (TextView) (convertView.findViewById(R.id.row_textview));

            text.setText(Integer.toString(alarm.getTime()));
            Log.i(TAG,"Set the text");
        }

        return convertView;
    }

}
