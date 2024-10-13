package com.example.hack_dearborn_3_project;

import java.io.Serializable;

public class UserGoals implements Serializable {
    private Integer userGoalId;
    private Integer goalId;
    private Integer username;
    private Integer savedNum;
    private Integer requiredNum;

    public UserGoals(Integer userGoalId, Integer goalId, Integer username, Integer savedNum, Integer requiredNum)
    {
        this.userGoalId = userGoalId;
        this.goalId = goalId;
        this.username = username;
        this.savedNum = savedNum;
        this.requiredNum=requiredNum;
    }

    public Integer getUserGoalId() {return userGoalId;}
    public void setUserGoalId(Integer userGoalId) {this.userGoalId = userGoalId;}
    public Integer getGoalId() {return goalId;}
    public void setGoalId(Integer goalId) {this.goalId = goalId;}
    public Integer getUsername() {return username;}
    public void setUsername(Integer username) {this.username = username;}
    public Integer getSavedNum() {return savedNum;}
    public void setSavedNum(Integer savedNum) {this.savedNum = savedNum;}
    public Integer getRequiredNum() {return requiredNum;}
    public void setRequiredNum(Integer requiredNum) {this.requiredNum = requiredNum;}
}
