package edu.ycp.cs482.workoutassistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    // Called when user clicks Workout List Button
    public void goToList(View view){
        // Create intent to start new activity
        Intent intent = new Intent(this, ViewWOListActivity.class);
        // Start the new activity
        startActivity(intent);
    }

    public void goToDBTest(View view){
        // Create intent and start new activity
        Intent intent = new Intent(this, DBTestActivity.class);
        startActivity(intent);

    }

    public void goToWOResults(View view){
        Intent intent = new Intent(this, WOResultsActivity.class);
        startActivity(intent);
    }
}
