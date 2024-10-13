package com.example.hack_dearborn_3_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="SaveCircleApp.db";
    private static final String USER_TABLE_NAME="Users";
    private static final String GOAL_TABLE_NAME="Goals";
    private static final String USERGOALS_TABLE_NAME="UserGoals";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables in the database
        //Execute the SQL statement on the database that was passed to the function OnCreated called db


        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                " (username VARCHAR(255) PRIMARY KEY NOT NULL, password VARCHAR(255), admin BOOLEAN);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + GOAL_TABLE_NAME +
                " (goalId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title VARCHAR(255), goalNum INTEGER, date VARCHAR(255));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + USERGOALS_TABLE_NAME +
                " (userGoalId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, goalId INTEGER NOT NULL, username VARCHAR (255), savedNum INTEGER, requiredNum INTEGER, " +
                "FOREIGN KEY (goalId) REFERENCES Goals(goalId) ON DELETE CASCADE, FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This is used if we change the version number of the database.
        //delete the table if you upgrade the database (change the version number in the constructor)
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + GOAL_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + USERGOALS_TABLE_NAME + ";");

        //create a new database once the old database has been deleted.
        onCreate(db);
    }

    public void initializeDB()
    {
        initializeUsers();
        initializeGoals();
    }


    public boolean initializeUsers()
    {
        if(numberOfRecordsInTable(USER_TABLE_NAME)==0)
        {
            //connect to the database
            //notice we are getting a writable database because we need to insert information into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //user insert statements
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('admin','admin','1');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('mpalmer','gamerfart2004','0');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('rbaker','jackstauber2004','0');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('jpalmer','enalover2004','0');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('rravengard','shadowsamongus69','0');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean initializeGoals()
    {
        if(numberOfRecordsInTable(GOAL_TABLE_NAME)==0)
        {
            //connect to the database
            //notice we are getting a writable database because we need to insert information into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //movie insert statements
            db.execSQL("INSERT INTO " + GOAL_TABLE_NAME + "(title, goalNum, date) VALUES('Default Goal','2000','4/12/2025');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public int numberOfRecordsInTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();

        return numRows;
    }

    public boolean validUsernamePasswordCombo(String username, String password)
    {
        boolean goodUsernamePassword = false;
        if(usernameExists(username))
        {
            String getUserInfo = "Select password from " + USER_TABLE_NAME + " WHERE username = '" + username + "';";

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(getUserInfo, null);

            if(cursor != null)
            {
                cursor.moveToFirst();

                //no need for a loop.  Should only be one thing returned to us
                //we do not need to use getcolumindex because this should only return us one column because we are selecting password only in our query
                if(password.equals(cursor.getString(0)))
                {
                    goodUsernamePassword = true;
                }
                else
                {
                    Log.d("BAD PASSWORD: ", "the password entered is not the correct password for that username");
                    goodUsernamePassword = false;
                }
            }

            db.close();
        }
        else
        {
            Log.d("BAD USERNAME: ", "the username entered was not found in the db");
            goodUsernamePassword = false;
        }


        return goodUsernamePassword;

    }

    public boolean usernameExists(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String checkUsername = "Select count(username) from " + USER_TABLE_NAME + " where username = '" + username + "';";
        Cursor cursor = db.rawQuery(checkUsername, null);
        //move cursor to the first thing because there should only be one thing returned.
        cursor.moveToFirst();
        //give getInt 0 for the first thing that is returned.  This should always return one thing because I am using the count function in sql
        //using getInt because count will return an int.
        int count = cursor.getInt(0);

        db.close();

        if(count != 0) {
            return true;
        } else
        {
            return false;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Users> getAllUsers()
    {
        ArrayList<Users> listUsers = new ArrayList<Users>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME + " ORDER BY username;";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        String uname;
        String pword;
        boolean admin;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                uname = cursor.getString(cursor.getColumnIndex("username"));
                pword = cursor.getString(cursor.getColumnIndex("password"));
                int adminInt = cursor.getInt(cursor.getColumnIndex("admin"));

                // Convert the integer value to boolean
                admin = (adminInt == 1);

                //add the returned results to my list
                listUsers.add(new Users(uname, pword, admin));
            }
            while(cursor.moveToNext());
        }
        db.close();
        return listUsers;
    }

    //USER CRUD====================================================================================
    public void addNewUser(Users u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES ('" + u.getUname() + "','" + u.getPword() + "','" + u.isAdmin() + "');");

        db.close();
    }

    public void deleteUser(String u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //create our delete command
        db.execSQL("DELETE FROM " + USER_TABLE_NAME + " WHERE username = '" + u + "';");

        //close the database
        db.close();
    }
    public void updateUser(String username, String password, int isAdmin)
    {
        //get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create out update command
        //needs to look like this:
        //UPDATE users SET firstname = 'Zack' , lastname = 'Moore' WHERE username = 'zmoore';
        String updateCommand = "UPDATE " + USER_TABLE_NAME + " SET password = '" + password + "' , admin = '" + isAdmin + "' WHERE username = '" + username + "';";

        db.execSQL(updateCommand);
        db.close();
    }

    //GOAL CRUD===================================================================================
    public void addGoal(String title, int goalNum, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + GOAL_TABLE_NAME +
                "(title, goalNum, date) VALUES('" + title + "','" + goalNum + "','" + date + "');");

        db.close();
    }
    public void deleteGoal(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + GOAL_TABLE_NAME + " WHERE goalId = '" + id + "';");
        db.close();
    }
    public void updateGoal(int goalId, String title, int goalNum, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateCommand = "UPDATE " + GOAL_TABLE_NAME + " SET title = '" + title + "' , goalNum = '" + goalNum + "' , date = '" + date + "';";

        db.execSQL(updateCommand);
        db.close();
    }

    //USERGOAL CRUD===================================================================================
    public void addUserGoal(int movieId, String username, int savedNum, int requiredNum)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + USERGOALS_TABLE_NAME +
                "(goalId, username, , requiredNum) VALUES('" + movieId + "','" + username + "','" + savedNum + "','" + requiredNum + "');");

        db.close();
    }

    public void deleteUserGoal(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + USERGOALS_TABLE_NAME + " WHERE userGoalId = '" + id + "';");
        db.close();
    }

    public void updateUserGoal(int userGoalId, int savedNum, int requiredNum)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateCommand = "UPDATE " + USERGOALS_TABLE_NAME + " SET savedNum = '" + savedNum + "' , requiredNum = '" + requiredNum + "' WHERE userGoalId = '" + userGoalId + "';";

        db.execSQL(updateCommand);
        db.close();
    }
}
