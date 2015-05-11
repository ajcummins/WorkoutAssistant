package edu.ycp.cs482.workoutassistant;

import java.util.ArrayList;

/**
 * Created by AJC on 5/11/2015.
 */
public class User {

    private int id;
    private String username;
    private String password;

    private ArrayList<Integer> routineIDList;

    public User(int inID, String inUser, String inPass){
        id = inID;
        username  = inUser;
        password = inPass;
    }

    public User(){

    }

    public ArrayList<Integer> getRoutineList() {
        return routineIDList;
    }

    public void setRoutineList(ArrayList<Integer> routineList) {
        this.routineIDList = routineList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
