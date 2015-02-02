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
    
    
    private RadioButton[] radioButtons= new RadioButton[4];
    private ToggleButton toggleButton;
    final int[] buttonIDS = {R.id.RadioButton01,R.id.RadioButton02,R.id.RadioButton03,R.id.RadioButton04};
    private final int[] ORIENTATION = {ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE,ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton1);
        for(int i=0;i<4;++i){
            radioButtons[i] = (RadioButton)findViewById( buttonIDS[i] );
            final int cur = i;
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(MainActivity.this,
                            OrientationServiceManager.class);
                    startIntent.setAction(Constants.ACTION.SET_ORIENTATION);
                    if(Preference.getInstance(getApplicationContext()).getServiceStatus()==true){
                        startIntent.putExtra("current_orientation", ORIENTATION[cur]);
                        startService(startIntent);
                        for(int j=0;j<4;++j){
                            if(cur!=j){
                                radioButtons[j].setChecked(false);
                            }
                        }
                    }else{
                        radioButtons[cur].setChecked(false);
                    }
                }
            });
        }
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    Intent startIntent = new Intent(MainActivity.this,
                            OrientationServiceManager.class);
                    startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    startService(startIntent);
                } else {
                    for(int i=0;i<4;++i){
                        radioButtons[i].setChecked(false);
                    }
                    
                    Intent endIntent = new Intent(MainActivity.this,
                            OrientationServiceManager.class);
                    endIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                    startService(endIntent);
                }
            }
        });
    }
    private void restoreToLastSaveState(){
        for (int i = 0; i < 4; ++i) {
            if( i == Preference.getInstance(getApplicationContext()).getCurrent_orientation() ){
                radioButtons[i].setChecked(true);
            }else{
                radioButtons[i].setChecked(false);
            }
        }
        toggleButton.setChecked(  Preference.getInstance(getApplicationContext()).getServiceStatus()==true );
    }
    @Override
    protected void onStart() {
        super.onStart();
        restoreToLastSaveState();
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
}
