package me.gustavozapata.today;

public class Task {
    private String description;
    private boolean isComplete;
    private String category;

    public Task(String description, String category) {
        this.description = description;
        this.isComplete = false;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
