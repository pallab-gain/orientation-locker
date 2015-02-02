package forgroundservicepkg;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import MyUtils.Constants;
import MyUtils.Preference;
import warwagondev.com.orientationlocker.MainActivity;
import warwagondev.com.orientationlocker.R;

public class  OrientationServiceManager extends Service {
    public OrientationServiceManager() {
    }

    private WindowManager windowManager;
    private View mOverlayView;
    //screen parameters
    final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);
    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        layoutParams.gravity = Gravity.TOP | Gravity.CENTER;
        mOverlayView = new View(this);
        layoutParams.width = 0;
        layoutParams.height = 0;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            //start notification
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("Orientation Locker")
                    .setTicker("Orientation Locker")
                    .setContentText("Orientation Locker")
                    .setSmallIcon(R.drawable.ic_stat_device_screen_lock_rotation)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent);

            Notification cur_notification = builder.build();
            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, cur_notification);
            Preference.getInstance(getApplicationContext()).setServiceStatus(true);
        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            try {
                windowManager.removeView(mOverlayView);
            } catch (Exception e) {
                ;
            }finally {
                Preference.getInstance(getApplicationContext()).setServiceStatus(false);
                Preference.getInstance(getApplicationContext()).removeCurrentOrientation();
                stopForeground(true);
                stopSelf();
            }
        }else if (intent.getAction().equals(Constants.ACTION.SET_ORIENTATION)) {
            
            final int orientation = intent.getExtras().getInt("current_orientation");
            if (orientation != Preference.getInstance(getApplicationContext()).getCurrent_orientation()) {
                //set orientation
                Preference.getInstance(getApplicationContext()).setCurrent_orientation(orientation);
                layoutParams.screenOrientation = orientation;
                try{
                    windowManager.updateViewLayout(mOverlayView,layoutParams);
                }catch (Exception e){
                    windowManager.addView(mOverlayView,layoutParams);
                }
            }

        } else if (intent.getAction().equals(Constants.ACTION.BOOT_RECEIVE)) {
            //start notification
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("Orientation Locker")
                    .setTicker("Orientation Locker")
                    .setContentText("Orientation Locker")
                    .setSmallIcon(R.drawable.ic_stat_device_screen_lock_rotation)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent);

            Notification cur_notification = builder.build();
            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, cur_notification);
            
            if( Preference.getInstance(getApplicationContext()).getServiceStatus() ) {
                final int orientation = Preference.getInstance(getApplicationContext()).getCurrent_orientation();
                if(orientation!=Preference.getInstance(getApplicationContext()).UNDEFINED) {
                    layoutParams.screenOrientation = orientation;
                    windowManager.addView(mOverlayView, layoutParams);
                }
            }

        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null) {
            try {
                windowManager.removeView(mOverlayView);
            } catch (Exception e) {
                ;
            }
        }
        Preference.getInstance(getApplicationContext()).setServiceStatus(false);
        Preference.getInstance(getApplicationContext()).removeCurrentOrientation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
