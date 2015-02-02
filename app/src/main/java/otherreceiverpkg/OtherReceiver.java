package otherreceiverpkg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import MyUtils.Constants;
import forgroundservicepkg.OrientationServiceManager;

public class OtherReceiver extends BroadcastReceiver {
    public OtherReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        if(Preference.getInstance(context).getServiceStatus())return ;

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            //do nothing
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Intent startIntent = new Intent(context, OrientationServiceManager.class);
            startIntent.setAction(Constants.ACTION.ON_SCREEN_ON);
            context.startService(startIntent);
        } else if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent startIntent = new Intent(context, OrientationServiceManager.class);
            startIntent.setAction(Constants.ACTION.BOOT_RECEIVE);
            context.startService(startIntent);
        }
    }
}
