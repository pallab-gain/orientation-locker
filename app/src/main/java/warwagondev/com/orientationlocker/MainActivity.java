package warwagondev.com.orientationlocker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ToggleButton;


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

        landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        revLandscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        revPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        /*
        Intent intent = new Intent(MainActivity.this, OrientationServiceManager.class);
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(intent);
        */
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
