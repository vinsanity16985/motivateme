package apps.motivateme;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity{
    private VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = (VideoView)findViewById(R.id.videoView);

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
                    //Release to save system resources
                    mp.release();

                    //Shut Down App, IDK what 0 means
                    System.exit(0);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
