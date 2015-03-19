package edu.ycp.cs482.workoutassistant;

/**
 * Created by AJC on 3/19/2015.
 */
public class Workout {

    private int id;
    private String workoutname;

    public Workout(){

    }

    public Workout(String inWorkoutName)
    {
        this.workoutname = inWorkoutName;
    }

    public void SetID(int inId){
        this.id = inId;
    }

    public int getID() {
        return this.id;
    }

    public void setWorkoutName(String inWorkoutName){
        this.workoutname = inWorkoutName;
    }

    public String getWorkoutName(){
        return this.workoutname;
    }

}
