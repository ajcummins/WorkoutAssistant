package edu.ycp.cs482.workoutassistant;

import java.util.ArrayList;

/**
 * Created by AJC on 5/11/2015.
 */
public class Routine {

    private int id;
    private String routineName;

    private ArrayList<Integer> workoutIDList;

    public Routine(){

    }

    public Routine(int inID, String inName){
        id = inID;
        routineName = inName;
    }

    public ArrayList<Integer> getWorkoutList() {
        return workoutIDList;
    }

    public void setWorkoutList(ArrayList<Integer> workoutList) {
        this.workoutIDList = workoutList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

}
