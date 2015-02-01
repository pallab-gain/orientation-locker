package warwagondev.com.orientationlocker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import MyUtils.Constants;
import MyUtils.Preference;
import forgroundservicepkg.OrientationServiceManager;


public class MainActivity extends Activity {

    private RadioButton landscape;
    private RadioButton revLandscape;
    private RadioButton portrait;
    private RadioButton revPortrait;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        landscape = (RadioButton)findViewById(R.id.radioButton);
        revLandscape = (RadioButton)findViewById(R.id.radioButton2);
        portrait = (RadioButton)findViewById(R.id.radioButton3);
        revPortrait = (RadioButton)findViewById(R.id.radioButton4);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setChecked(  Preference.getInstance(getApplicationContext()).getServiceStatus() );

        landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this,
                        OrientationServiceManager.class);
                startIntent.setAction(Constants.ACTION.SET_ORIENTATION);
                if( Preference.getInstance(getApplicationContext()).getServiceStatus() ) {
                    Preference.getInstance(getApplicationContext()).setCurrent_orientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    disableOthers(1);
                    startService(startIntent);
                }else{
                    disableOthers(5);
                }
            }
        });
        revLandscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this,
                        OrientationServiceManager.class);
                startIntent.setAction(Constants.ACTION.SET_ORIENTATION);
                if( Preference.getInstance(getApplicationContext()).getServiceStatus() ) {
                    Preference.getInstance(getApplicationContext()).setCurrent_orientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    disableOthers(2);
                    startService(startIntent);
                }else{
                    disableOthers(5);
                }
            }
        });
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this,
                        OrientationServiceManager.class);
                startIntent.setAction(Constants.ACTION.SET_ORIENTATION);
                if( Preference.getInstance(getApplicationContext()).getServiceStatus() ) {
                    Preference.getInstance(getApplicationContext()).setCurrent_orientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    disableOthers(3);
                    startService(startIntent);
                }else{
                    disableOthers(5);
                }
            }
        });
        revPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this,
                        OrientationServiceManager.class);
                startIntent.setAction(Constants.ACTION.SET_ORIENTATION);
                if( Preference.getInstance(getApplicationContext()).getServiceStatus() ) {
                    Preference.getInstance(getApplicationContext()).setCurrent_orientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    disableOthers(4);
                    startService(startIntent);
                }else{
                    disableOthers(5);
                }
            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    Intent startIntent = new Intent(MainActivity.this,
                            OrientationServiceManager.class);
                    startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    startService(startIntent);
                } else {
                    disableOthers(5);
                    Intent endIntent = new Intent(MainActivity.this,
                            OrientationServiceManager.class);
                    endIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                    startService(endIntent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void disableOthers(int cur){
        switch (cur){
            case 0:
                break;
            case 1:
                revLandscape.setChecked(false);
                portrait.setChecked(false);
                revPortrait.setChecked(false);
                break;
            case 2:
                landscape.setChecked(false);
                portrait.setChecked(false);
                revPortrait.setChecked(false);
                break;
            case 3:
                landscape.setChecked(false);
                revLandscape.setChecked(false);
                revPortrait.setChecked(false);
                break;
            case 4:
                landscape.setChecked(false);
                revLandscape.setChecked(false);
                portrait.setChecked(false);
            default:
                landscape.setChecked(false);
                revLandscape.setChecked(false);
                portrait.setChecked(false);
                revPortrait.setChecked(false);
                break;

        }
    }
}
