package apps.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.VideoView;

public class MainActivity extends Activity {
    private VideoView video;
    private boolean hasPlayed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        video = (VideoView)findViewById(R.id.videoView);
        hasPlayed = false;

        startService(new Intent(this, MyService.class));

        playVideo();
    }

    @Override
    public void onBackPressed(){
        //Disable Back Button
        return;
    }

    public void playVideo(){
        try{
            Uri vid = Uri.parse("android.resource://apps.motivateme/raw/sc");

            video.setVideoURI(vid);
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    video.start();
                }
            });

            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(!hasPlayed){
                        playVideo();
                    }
                    else{
                        //Release to save system resources
                        mp.release();

                        //Shut Down App, IDK difference between 0 and 1
                        System.exit(0);
                    }
                    hasPlayed = true;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
