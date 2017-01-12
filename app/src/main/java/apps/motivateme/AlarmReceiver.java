package apps.motivateme;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent){
        Intent service = new Intent(context, AlarmService.class);

        startWakefulService(context, service);
    }

}
