package edu.ycp.cs482.workoutassistant;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import edu.ycp.cs482.workoutassistant.provider.woAppContentProvider;


public class WOResultsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    // Intent Extra
    public final static String EXTRA_RESULT_WO_ID = "edu.ycp.cs482.workoutassistant.RESULTWOID";

    // Adapter to display list data
    SimpleCursorAdapter myAdapter;

    // Workout columns to retrieve
    static final String[] PROJECTION = new String[] {
            DBHandler.COLUMN_ID,
            DBHandler.COLUMN_WORKOUTNAME
    };

    // Selection criteria
    static final String SELECTION = "((" +
            DBHandler.COLUMN_WORKOUTNAME + " NOTNULL) AND (" +
            DBHandler.COLUMN_WORKOUTNAME + " != '' ))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create progress bar
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // add progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        // specify what columns go into which views for the cursor adapter
        String[] fromColumns = {DBHandler.COLUMN_WORKOUTNAME};
        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1?

        // create an empty adapter to display the loaded data.
        // pass null for the cursor, then update it using onLoadFinished()
        myAdapter = new SimpleCursorAdapter(this,android.R.layout.activity_list_item,null,fromColumns,toViews,0);
        setListAdapter(myAdapter);

        // Setup the loader. Reconnect to existing or start a new loader
        getLoaderManager().initLoader(0,null,this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_list, menu);
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // create and return CursorLoader
        return new CursorLoader(this, woAppContentProvider.CONTENT_URI, PROJECTION,SELECTION,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Swap new cursor in
        myAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Close / null the Cursor
        myAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        // Use the id given from the item to open the workout view.
        Intent intent = new Intent(this, AddResultsActivity.class);
        String viewWOID = Integer.toString((int) id);
        intent.putExtra(EXTRA_RESULT_WO_ID,viewWOID);
        startActivity(intent);
    }

}
