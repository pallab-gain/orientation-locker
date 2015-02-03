package otherreceiverpkg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import MyUtils.Constants;
import forgroundservicepkg.OrientationServiceManager;

public class BootReceive extends BroadcastReceiver {
    public BootReceive() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent startIntent = new Intent(context, OrientationServiceManager.class);
            startIntent.setAction(Constants.ACTION.BOOT_RECEIVE);
            context.startService(startIntent);
        }
    }
}
