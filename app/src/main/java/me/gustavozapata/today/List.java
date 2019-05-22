package me.gustavozapata.today;

import java.util.ArrayList;

public class List {
    private String listName;
    private ArrayList<Task> tasks = new ArrayList<>();

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Task task) {
        this.tasks.add(task);
    }
}
