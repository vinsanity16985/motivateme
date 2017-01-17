package apps.motivateme.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import apps.motivateme.R;
import apps.motivateme.activities.VideoActivity;
import apps.motivateme.utils.AppUtils;

public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(!AppUtils.isAppInForeground(this) || AppUtils.isPhoneLocked(this)){          //If the app is not in the foreground or the phone is locked
            AppUtils.turnOnScreen(getBaseContext());
            NotificationManager alarmNotificationManager = (NotificationManager) this
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            String title = "Alarm";
            String msg = "Wake Up";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.logo)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setContentText(msg);

            builder.setAutoCancel(true);
            alarmNotificationManager.notify(1, builder.build());
        }

        Intent videoIntent = new Intent(this, VideoActivity.class);         //Intent to open VideoActivity
        videoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(videoIntent);
    }
}
