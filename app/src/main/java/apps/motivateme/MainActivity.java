package apps.motivateme;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
//    private boolean hasPlayed;

    private static final String TAG = "MainActivity";

    private static ArrayList<Alarm> alarmList;
    private static ListAdapter adapter;

    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Started Activity");
        setContentView(R.layout.activity_main);

        inflater = getLayoutInflater();

        alarmList = new ArrayList<Alarm>();
        int time = 10;
        Alarm alarm = new Alarm(time);
        alarmList.add(alarm);

        adapter = new MyAdapter(this,R.layout.row_layout, alarmList);

        setListAdapter(adapter);
        Log.i(TAG, "Set list adapter");
    }











//    public void playVideo(){
//        try{
//            Uri vid = Uri.parse("android.resource://apps.motivateme/raw/sc");
//
//            video.setVideoURI(vid);
//            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    video.start();
//                }
//            });
//
//            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    if(!hasPlayed){
//                        playVideo();
//                    }
//                    else{
//                        //Release to save system resources
//                        mp.release();
//
//                        //Shut Down App, IDK difference between 0 and 1
//                        System.exit(0);
//                    }
//                    hasPlayed = true;
//                }
//            });
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
