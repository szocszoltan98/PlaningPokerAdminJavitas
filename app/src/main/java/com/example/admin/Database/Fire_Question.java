package com.example.admin.Database;

public class Fire_Question {
    private  String ID;
    private String question;
    private String group;
    private  boolean status;

    public Fire_Question() {
    }

    public Fire_Question(String question, String group, boolean status) {
        this.question = question;
        this.group = group;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
