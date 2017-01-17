package apps.motivateme.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by vinsa_000 on 1/16/2017.
 */
public class AppUtils {

    private static final String TAG = "AppUtils";

    private AppUtils(){}

    public static boolean isAppInForeground(Context context){
        ActivityManager aMgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo foregroundTaskInfo = aMgr.getRunningTasks(1).get(0);

        String foregroundTaskPackageName = foregroundTaskInfo .topActivity.getPackageName();
        PackageManager pm = context.getPackageManager();
        PackageInfo foregroundAppPackageInfo = null;
        try {
            foregroundAppPackageInfo = pm.getPackageInfo(foregroundTaskPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String foregroundAppName = foregroundAppPackageInfo.applicationInfo.loadLabel(pm).toString();
        String appName = "MotivateMe";
        boolean appInForeground = foregroundAppName.toLowerCase().equals(appName.toLowerCase());

        return appInForeground;          //If equal then return true, otherwise false
    }

    public static boolean isPhoneLocked(Context context){
        boolean isLocked = false;
        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if( myKM.inKeyguardRestrictedInputMode()) {
            isLocked = true;
        }
        return isLocked;
    }

    public static void turnOnScreen(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wl.acquire();
        wl.release();
    }
}
