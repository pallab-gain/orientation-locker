package MyUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xerxes on 2/1/15.
 * "I have not failed, I have just found 10000 ways that won't work."
 */
public class Preference {
    public final int UNDEFINED = 1023456789;
    private static Preference ourInstance = null;
    private final String MyPREFERENCES = "OrientationLocker";
    SharedPreferences sharedpreferences;

    public static Preference getInstance(Context context) {
        if( ourInstance==null )
            ourInstance = new Preference(context);
        return ourInstance;
    }

    private Preference(Context context) {
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    public int getCurrent_orientation(){
        return sharedpreferences.getInt("current_orientation", UNDEFINED);
    }
    public boolean getServiceStatus(){
        return sharedpreferences.getBoolean("service_status", false);
    }
    public void setServiceStatus(boolean status){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("service_status",status);
        editor.commit();
    }
    public void setCurrent_orientation(int orientation){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("current_orientation",orientation);
        editor.commit();
    }



}
