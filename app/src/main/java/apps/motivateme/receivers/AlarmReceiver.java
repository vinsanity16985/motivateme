package apps.motivateme.receivers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import apps.motivateme.services.AlarmService;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent){
        Intent service = new Intent(context, AlarmService.class);

        startWakefulService(context, service);
    }

}
