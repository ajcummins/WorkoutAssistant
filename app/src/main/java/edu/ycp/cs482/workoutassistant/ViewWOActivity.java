package edu.ycp.cs482.workoutassistant;

import android.content.ContentResolver;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewWOActivity extends ActionBarActivity {

    private TextView woIDResult;
    private TextView woNameResult;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wo);

        // Obtain view objects
        woIDResult = (TextView) findViewById(R.id.woViewIDResult);
        woNameResult = (TextView) findViewById(R.id.woViewNameResult);

        // Use the ID passed from the intent to load the workout data
        Intent intent = getIntent();

        int inID = Integer.parseInt(intent.getStringExtra(ViewWOListActivity.EXTRA_WO_ID));

        // Use the dbHandler to obtain the workout data
        dbHandler = new DBHandler(this);
        Workout inWO = dbHandler.findWorkout(inID);

        woIDResult.setText("" + inWO.getID());
        woNameResult.setText(inWO.getWorkoutName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_wo, menu);
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
