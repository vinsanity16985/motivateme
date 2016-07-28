package apps.motivateme;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    private MediaPlayer mp;
    private SurfaceView surface;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.steph_curry);
        surface = (SurfaceView)findViewById(R.id.surfaceView1);
        holder = surface.getHolder();

        startService(new Intent(this, MyService.class));
    }

    @Override
    public void onBackPressed(){
        //Disable Back Button
        return;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Will set the display once the surface has been created and then play the video
        mp.setDisplay(holder);
        playVideo();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        return;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        return;
    }

    public void playVideo(){
        try{
            mp.start();

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
