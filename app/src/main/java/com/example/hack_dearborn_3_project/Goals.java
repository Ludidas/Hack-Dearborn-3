package com.example.hack_dearborn_3_project;

import java.io.Serializable;

public class Goals implements Serializable {
    private Integer goalId;
    private String title;
    private Integer goalNum;
    private String date;

    public Goals(Integer goalId, String title, Integer goalNum, String date)
    {
        this.goalId = goalId;
        this.title = title;
        this.goalNum = goalNum;
        this.date = date;

    }

    public Integer getGoalId()
    {
        return goalId;
    }
    public void setGoalId(Integer goalId){this.goalId=goalId;}
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public Integer getGoalNum()
    {
        return goalNum;
    }
    public void setGoalNum(Integer goalNum)
    {
        this.goalNum = goalNum;
    }
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
}
