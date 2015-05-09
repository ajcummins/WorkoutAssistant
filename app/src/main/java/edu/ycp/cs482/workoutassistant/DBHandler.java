package edu.ycp.cs482.workoutassistant;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    // DB Version
    private static final int DATABASE_VERSION = 1;
    // DB TABLE CONSTANTS
    private static final String DATABASE_NAME = "workoutDB.db";
    public static final String TABLE_WORKOUTS = "workouts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORKOUTNAME = "workoutname";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    /*
    Since Version 1 of the DB only has the _id field as the primary key, we must use that when searching...
     */

    // Add workout return boolean successful/unsuccessful
    public boolean addWorkout(Workout inWorkout){
        // Use boolean to determine successful transaction
        boolean success = true;
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUTNAME, inWorkout.getWorkoutName());
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.insert(TABLE_WORKOUTS,null,values);
        }
        catch (Exception e){
            success = false;
        }
        db.close();
        return success;
    }


    public boolean deleteWorkout(int inID){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = true;
        try{
            db.delete(TABLE_WORKOUTS, COLUMN_ID + "=" + inID, null);
        }
        catch (Exception e){
            success = false;
        }
        db.close();
        return success;
    }

    public boolean updateWorkout(Workout inWorkout){
        // Initialization
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = true;
        // Create query
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUTNAME, inWorkout.getWorkoutName());
        String whereClause =  COLUMN_ID + " = ?";
        String[] whereArgs = new String[] {
                "" + inWorkout.getID()
        };
        try{
            db.update(TABLE_WORKOUTS,values,whereClause,whereArgs);
        }
        catch(Exception e){
            success = false;
        }
        db.close();
        return success;
    }

    public Workout findWorkout(int inID){
        // ini
        SQLiteDatabase db = this.getWritableDatabase();
        // Create query
        String[] tableColumns = new String[] {
                COLUMN_ID,
                COLUMN_WORKOUTNAME
        };
        String whereClause =  COLUMN_ID + " = ?";
        String[] whereArgs = new String[] {
                "" + inID
        };

        Cursor cursor;
        Workout workout;

        cursor = db.query(TABLE_WORKOUTS,tableColumns,whereClause,whereArgs,null,null,null);
        workout = new Workout();
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();

            workout.setID(Integer.parseInt(cursor.getString(0)));
            workout.setWorkoutName(cursor.getString(1));
        }
        else{
            workout = null;
        }
        /*
        try{

        }
        catch(Exception e){
            workout = null;
        }
        */
        cursor.close();
        db.close();
        return workout;

    }

    //TODO: Write this method that returns a list of all Workouts in the workout table
    public ArrayList<Workout> findAllWorkouts(){
        return null;
    }

}
