package edu.ycp.cs482.workoutassistant;

/**
 * Created by AJC on 3/19/2015.
 */
public class Workout {

    private int id;
    private String workoutname;

    /*
    Have to record the units of the workout
    Time/Distance
    Weight/Rep

    Have to record Muscle Group
    private enum muscleGroup
        -> Upper Body, Core, Lower Body
        OR
        -> Shoulders, Back, Arms, Legs, Core, Chest

    Record Muscle's worked

    arraylist enum muscelsEngaged

    pectoral, bicep, tricep...
     */

    public Workout(){

    }

    public Workout(String inWorkoutName)
    {
        this.workoutname = inWorkoutName;
    }

    public void setID(int inId){
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
