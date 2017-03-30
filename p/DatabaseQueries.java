package com.example.sarveshtank.securenotes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by Sarvesh.Tank on 3/30/2017.
 */

public class DatabaseQueries {

    public static long saveNotes(Context context, String heading, String data, String encToken){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TableColumn.HEADING, heading);
        cv.put(DatabaseContract.TableColumn.DATA, data);
        cv.put(DatabaseContract.TableColumn.ENCRYPTION_TOKEN, encToken);
        long l = db.insert(DatabaseContract.TableColumn.TABLE_NAME, null, cv);
        db.close();
        return l;
    }

    public static long updateNotes(Context context, int id, String heading, String data){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TableColumn.HEADING, heading);
        cv.put(DatabaseContract.TableColumn.DATA, data);
        long l = db.update(DatabaseContract.TableColumn.TABLE_NAME, cv, DatabaseContract.TableColumn._ID, new String[]{String.valueOf(id)});
        db.close();
        return l;
    }

    public  static long updateEncToken(Context context, int id, String encToken){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long l = db.update(DatabaseContract.TableColumn.TABLE_NAME, cv, DatabaseContract.TableColumn._ID, new String[]{String.valueOf(id)});
        db.close();
        return l;
    }

    public static long deleteNote(Context context, int id, String heading, String data, String encToken){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        long l = db.delete(DatabaseContract.TableColumn.TABLE_NAME, DatabaseContract.TableColumn._ID, new String[]{String.valueOf(id)});
        db.close();
        return l;
    }

    public static Cursor selectNotesAll(Context context){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor query = db.query(DatabaseContract.TableColumn.TABLE_NAME, new String[]{DatabaseContract.TableColumn._ID, DatabaseContract.TableColumn.HEADING, DatabaseContract.TableColumn.ENCRYPTION_TOKEN}, null, null, null, null, DatabaseContract.TableColumn.CREATED_ON);
        db.close();
        return query;
    }

    public static Cursor selectNotesById(Context context, int id){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor query = db.query(DatabaseContract.TableColumn.TABLE_NAME, new String[]{DatabaseContract.TableColumn._ID, DatabaseContract.TableColumn.HEADING, DatabaseContract.TableColumn.DATA}, DatabaseContract.TableColumn._ID, new String[]{String.valueOf(id)}, null, null, null);
        db.close();
        return query;
    }
}
