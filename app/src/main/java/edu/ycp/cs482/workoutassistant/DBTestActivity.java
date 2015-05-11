package edu.ycp.cs482.workoutassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AJC on 4/26/2015.
 */
public class DBTestActivity extends ActionBarActivity{

    EditText woNameBox;
    EditText woIDBox;
    TextView resultID;
    TextView resultWOName;

    //Toast related variables
    Toast toast;
    Context context;
    int duration = Toast.LENGTH_SHORT;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        context = getApplicationContext();

        //Obtain View Objects
        woNameBox = (EditText) findViewById(R.id.woEditText);
        woIDBox = (EditText) findViewById(R.id.idEditText);
        resultID = (TextView) findViewById(R.id.idResultText);
        resultWOName = (TextView) findViewById(R.id.woResultText);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dbtest, menu);
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

    public void findWorkout(View view){
        DBHandler handler = new DBHandler(this);

        // If the ID field is not null
        if(!woIDBox.getText().toString().isEmpty()){
            // obtain the workout with the matching ID field
            Workout tempWorkout = handler.findWorkout((Integer.parseInt(woIDBox.getText().toString())));
            // If the returned Workout is not null output the object's data onto the view
            if(tempWorkout != null){
                resultID.setText(String.valueOf(tempWorkout.getID()));
                resultWOName.setText(String.valueOf(tempWorkout.getWorkoutName()));
                toast = Toast.makeText(context,"Result Found",duration);
                toast.show();
            }
            else{
                resultID.setText("Not Found");
                resultWOName.setText("Not Found");
                toast = Toast.makeText(context,"Not Found",duration);
                toast.show();
            }

        }

    }

    public void addWorkout(View view){
        DBHandler handler = new DBHandler(this);
        Workout tempWO = new Workout();
        boolean success;

        // if the fields are not empty, add them to a Workout object and call the handler
        if(!woNameBox.getText().toString().isEmpty()){
            tempWO.setWorkoutName(woNameBox.getText().toString());
            success = handler.addWorkout(tempWO);
            // Use the boolean to tell if the operation completed or not.
            if(success){
                toast = Toast.makeText(context,"Add Successful",duration);
                toast.show();
            }
            else{
                toast = Toast.makeText(context,"Add Unsuccessful",duration);
                toast.show();
            }
        }
        else{
            toast = Toast.makeText(context,"Input incorrect try again",duration);
            toast.show();
        }


    }

    public void updateWorkout(View view){
        DBHandler handler = new DBHandler(this);
        Workout tempWO = new Workout();
        boolean success;

        // if the fields are not empty, add them to a Workout object and call the handler
        if(!woNameBox.getText().toString().isEmpty() && !woIDBox.getText().toString().isEmpty()){
            tempWO.setID(Integer.parseInt(woIDBox.getText().toString()));
            tempWO.setWorkoutName(woNameBox.getText().toString());
            success = handler.updateWorkout(tempWO);
            // Use the boolean to tell if the operation completed or not.
            if(success){
                toast = Toast.makeText(context,"Update Successful",duration);
                toast.show();
            }
            else{
                toast = Toast.makeText(context,"Update Unsuccessful",duration);
                toast.show();
            }
        }
        else{
            toast = Toast.makeText(context,"Input incorrect try again",duration);
            toast.show();
        }
    }

    public void deleteWorkout(View view){
        DBHandler handler = new DBHandler(this);
        Workout tempWO = new Workout();
        boolean success;

        if(!woIDBox.getText().toString().isEmpty()) {
            success = handler.deleteWorkout(Integer.parseInt(woIDBox.getText().toString()));
            if(success){
                toast = Toast.makeText(context,"Delete Successful",duration);
                toast.show();
            }
            else{
                toast = Toast.makeText(context,"Delete Unsuccessful",duration);
                toast.show();
            }
        }
        else{
            toast = Toast.makeText(context, "Input incorrect try again", duration);
            toast.show();
        }
    }

}
