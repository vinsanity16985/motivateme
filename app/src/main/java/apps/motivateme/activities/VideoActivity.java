package apps.motivateme.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import apps.motivateme.R;

public class VideoActivity extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener{

    private VideoView mediaPlayer;
    private MediaController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getActionBar().hide();
        setAudioToMax();

        mediaPlayer = (VideoView) findViewById(R.id.videoView);
        controller = new MediaController(this);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.sc;

        controller.setAnchorView(mediaPlayer);


        mediaPlayer.setMediaController(controller);
        mediaPlayer.setVideoURI(Uri.parse(path));
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);

        if(savedInstanceState != null){
            mediaPlayer.seekTo(savedInstanceState.getInt("current position"));
        }

        setVideoDimensions();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("current position", mediaPlayer.getCurrentPosition());
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    private void setVideoDimensions(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mediaPlayer.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        mediaPlayer.setLayoutParams(params);
    }

    private void setAudioToMax(){
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
    }
}
