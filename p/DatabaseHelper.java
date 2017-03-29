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
        final String createQuery = "CREATE TABLE "+ DatabaseContract.TableColumn.TABLE_NAME+" ("
                +DatabaseContract.TableColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +DatabaseContract.TableColumn.HEADING + " VARCHAR(100),  "
                +DatabaseContract.TableColumn.DATA + " VARCHAR(1000), "
                +DatabaseContract.TableColumn.CREATED_ON+ " TIMESTAMP, "
                +DatabaseContract.TableColumn.LAST_MODIFIED_ON+ " TIMESTAMP, "
                +DatabaseContract.TableColumn.ENCRYPTION_TOKEN+ " VARCHAR(9), "
                +");";

        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
