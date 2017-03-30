package com.example.sarveshtank.securenotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sarvesh.Tank on 3/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usernotes.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_QUERY = "CREATE TABLE "+ DatabaseContract.TableColumn.TABLE_NAME+" ("
                +DatabaseContract.TableColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +DatabaseContract.TableColumn.HEADING + " TEXT,  "
                +DatabaseContract.TableColumn.DATA + " TEXT, "
                +DatabaseContract.TableColumn.CREATED_ON+ " TIMESTAMP DATETIME  CURRENT_TIMESTAMP, "
                +DatabaseContract.TableColumn.LAST_MODIFIED_ON+ " TIMESTAMP DATETIME  CURRENT_TIMESTAMP, "
                +DatabaseContract.TableColumn.ENCRYPTION_TOKEN+ " VARCHAR(9), "
                +");";

        db.execSQL(CREATE_QUERY);


        final String UPDATE_TIME_TRIGGER =
                "CREATE TRIGGER update_time_trigger" +
                        "  AFTER UPDATE ON " + DatabaseContract.TableColumn.TABLE_NAME + " FOR EACH ROW" +
                        "  BEGIN " +
                        "  UPDATE " + DatabaseContract.TableColumn.TABLE_NAME +
                        "  SET " + DatabaseContract.TableColumn.LAST_MODIFIED_ON + " = current_timestamp" +
                        "  WHERE " + DatabaseContract.TableColumn._ID + " = old." + DatabaseContract.TableColumn._ID + ";" +
                        "  END";

        db.execSQL(UPDATE_TIME_TRIGGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
