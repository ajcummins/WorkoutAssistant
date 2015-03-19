package edu.ycp.cs482.workoutassistant;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workoutDB.db";
    public static final String TABLE_WORKOUTS = "workouts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WORKOUTNAME = "workoutname";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE_WORKOUTS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_WORKOUTNAME + " TEXT" + ")";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        onCreate(db);
    }

    public void addWorkout(Workout inWorkout){
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUTNAME, inWorkout.getWorkoutName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_WORKOUTS,null,values);
        db.close();
    }

    public ArrayList<Workout> findAllWorkouts(){
        String query = "Select * FROM " + TABLE_WORKOUTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        /* not sure in what form the data will be returned... */

        return null;
    }

    public Workout findWorkout(String inWorkoutName){
        String query = "Select * FROM " + TABLE_WORKOUTS + " WHERE " + COLUMN_WORKOUTNAME +
                 " = " + inWorkoutName;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        Workout workout = new Workout();

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();

            workout.SetID(Integer.parseInt(cursor.getString(0)));
            workout.setWorkoutName(cursor.getString(1));
        }
        else{
            workout = null;
        }
        db.close();
        return workout;

    }

}
