package com.example.hack_dearborn_3_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GoalListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Goals> listOfGoals;

    public GoalListAdapter(Context c, ArrayList<Goals> ls)
    {
        context=c;
        listOfGoals=ls;
    }

    @Override
    public int getCount() {
        return listOfGoals.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfGoals.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater mInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        }

        //Find the GUI elements in my goal_cell


        Goals goal=listOfGoals.get(i);

        //Set the GUI elements in goal_cell.xml


        return convertView;
    }
}
