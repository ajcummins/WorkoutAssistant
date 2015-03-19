package edu.ycp.cs482.workoutassistant;


import android.os.AsyncTask;

import java.net.URL;

public class DBIniTestTask extends AsyncTask<URL, Integer, Long> {

    // Progress update
    protected void onProgressUpdate(Integer... progress){
        //setProgressPercent(progress[0]);
    }

    @Override
    protected Long doInBackground(URL... params) {
        return null;
    }

    // Post Execute
    protected void onPostExecute(Long result) {
        //showNotification("Database initialized");
    }
}
