package com.buildingfive.hwndk; 

import android.database.Cursor;
import android.text.format.Time;
import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pi";
    public static final String TABLE_RUNS = "runs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_TIME_FORMATTED = "time_formatted";
    public static final String COL_DATE_INSERTED = "dateinserted";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RUNS + "( " 
    		+ COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, " + COLUMN_TIME
            + " integer not null, " + COLUMN_TIME_FORMATTED
            + " text not null, " + COL_DATE_INSERTED
            + " text not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //version two won't destroy all data, will simply run 'ALTER TABLE' -- hopefully
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUNS);
        onCreate(db);
    }
    
    public void saveScore(String strMethod, String strScore, String strScoreFormatted){

    	Time now = new Time();
    	now.setToNow();
    	String strDate = String.valueOf(now.month + 1) + "/" + now.monthDay + "/" + now.year;
    			
    	String strSaveScore = "INSERT INTO " + TABLE_RUNS + " (" + COLUMN_NAME + ", " + 
    			COLUMN_TIME + ", " + COLUMN_TIME_FORMATTED + ", " + COL_DATE_INSERTED + ") VALUES ('" +
    			strMethod.replace("'", "''") + "', " + strScore + ", '" + strScoreFormatted + "', '" + strDate + "')";
    	
    	this.getReadableDatabase().execSQL(strSaveScore);
    }

    public Cursor getScores() {
        return this.getReadableDatabase().rawQuery("SELECT name, time, time_formatted FROM runs order by time asc", null);
    }
}