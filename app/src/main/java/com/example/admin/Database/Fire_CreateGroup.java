package com.example.admin.Database;

public class Fire_CreateGroup {
    private String groupName;
    private String groupCode;

    public Fire_CreateGroup()
    {

    }

    public Fire_CreateGroup(String groupName, String groupCode) {
        this.groupName = groupName;
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
