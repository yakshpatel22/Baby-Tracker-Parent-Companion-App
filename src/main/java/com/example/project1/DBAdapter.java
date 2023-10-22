package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_EVENT = "event";
    static final String KEY_YEAR = "year";
    static final String KEY_MONTH = "month";
    static final String KEY_DAY = "day";
    static final String KEY_HOUR = "hour";
    static final String KEY_MINUTE = "minute";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "myBaby";
    static final String DATABASE_TABLE = "babyrecorder";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE =
            "create table babyrecorder (_id integer primary key autoincrement, "
                    + "event text not null, year integer, month integer, day integer," +
                    "hour integer,minute integer);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert an event into the database---
    public long insertEvent(String event,int year,int month,int day,int hour,int minute)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EVENT,event);
        initialValues.put(KEY_YEAR,year);
        initialValues.put(KEY_MONTH,month);
        initialValues.put(KEY_DAY,day);
        initialValues.put(KEY_HOUR, hour);
        initialValues.put(KEY_MINUTE, minute);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteEvent(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public void deleteAllEvents()
    {
        db.delete(DATABASE_TABLE,null,null);
    }

    //---retrieves all the contacts---
    public Cursor getAllEvents()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_EVENT,
                KEY_YEAR, KEY_MONTH,KEY_DAY,KEY_HOUR,KEY_MINUTE}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getEvent(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_EVENT, KEY_YEAR,KEY_MONTH,KEY_DAY,KEY_HOUR,KEY_MINUTE},
                        KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateEvent(long rowId, String event, int year,int month,int day,int hour,
                               int minute)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EVENT, event);
        args.put(KEY_YEAR, year);
        args.put(KEY_MONTH, month);
        args.put(KEY_DAY, day);
        args.put(KEY_HOUR, hour);
        args.put(KEY_MINUTE, minute);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public void addEvent(long rowId, String event, int year,int month,int day,int hour,
                         int minute)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EVENT, event);
        args.put(KEY_YEAR, year);
        args.put(KEY_MONTH, month);
        args.put(KEY_DAY, day);
        args.put(KEY_HOUR, hour);
        args.put(KEY_MINUTE, minute);
        db.insert(DATABASE_TABLE,null,args);
    }
}

