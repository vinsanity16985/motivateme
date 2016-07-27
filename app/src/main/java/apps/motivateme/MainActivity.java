package apps.motivateme;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyService.class));

        playVideo();
    }

    public void playVideo(){
        try{
            MediaPlayer mp = MediaPlayer.create(this, R.raw.why_do_you_dream);
            mp.start();
            while(mp.isPlaying()){
                //Lock Home, Back and Settings Buttons

            }
            //Unlock all buttons

            //Release and nullify to not waste system resources
            mp.release();
            mp = null;

            //Shut Down the app, IDK fully what the purpose of the 0 is
            System.exit(0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
