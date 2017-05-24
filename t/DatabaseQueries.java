package com.whiteboard.securenotes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseQueries {

    public static long insertNotes(Context context, String heading, String data, int enc, String color){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TableColumn.HEADING, heading);
        cv.put(DatabaseContract.TableColumn.DATA, data);
        cv.put(DatabaseContract.TableColumn.ENCRYPTION_TOKEN, enc);
        cv.put(DatabaseContract.TableColumn.COLOR, color);
        long l = db.insert(DatabaseContract.TableColumn.TABLE_NAME, null, cv);
        db.close();
        return l;
    }

    public static long updateNotes(Context context, int id, String heading, String data, int enc, String color){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TableColumn.HEADING, heading);
        cv.put(DatabaseContract.TableColumn.DATA, data);
        cv.put(DatabaseContract.TableColumn.ENCRYPTION_TOKEN, enc);
        cv.put(DatabaseContract.TableColumn.COLOR, color);

        long l = db.update(DatabaseContract.TableColumn.TABLE_NAME, cv, DatabaseContract.TableColumn._ID+"=?", new String[]{String.valueOf(id)});
        db.close();
        return l;
    }

    public static long deleteNote(Context context, int id){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        long l = db.delete(DatabaseContract.TableColumn.TABLE_NAME, DatabaseContract.TableColumn._ID+"=?", new String[]{String.valueOf(id)});
        db.close();
        return l;
    }

    /*
    *
    * Input param:
    * 1. Context
    *
    * Output param:
    * 1. HEADING
    * 2. LAST_MODIFIED_ON
    * 3. COLOR
    * 4. _ID
    *
    */
    public static Cursor selectNotesAll(Context context, String sortBy){
        if(sortBy.equals(DatabaseContract.TableColumn.LAST_MODIFIED_ON)){
            sortBy+=" desc";
        }
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor query = db.query(
                DatabaseContract.TableColumn.TABLE_NAME,
                new String[]{
                        DatabaseContract.TableColumn.HEADING,
                        DatabaseContract.TableColumn.LAST_MODIFIED_ON,
                        DatabaseContract.TableColumn.COLOR,
                        DatabaseContract.TableColumn._ID},
                null, null, null, null, sortBy);
        return query;
    }


    /*
    *
    * Input param:
    * 1. Context
    * 2. Id
    *
    * Output param:
    * 1. HEADING
    * 2. DATA
    * 3. ENCRYPTION_TOKEN
    * 4. LAST_MODIFIED_ON
    * 5. COLOR
    * 6. ID
    *
    */
    public static Cursor selectNotesById(Context context, int id){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor query = db.query(DatabaseContract.TableColumn.TABLE_NAME, new String[]{DatabaseContract.TableColumn.HEADING, DatabaseContract.TableColumn.DATA, DatabaseContract.TableColumn.ENCRYPTION_TOKEN, DatabaseContract.TableColumn.LAST_MODIFIED_ON, DatabaseContract.TableColumn.COLOR, DatabaseContract.TableColumn._ID }, DatabaseContract.TableColumn._ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
        return query;
    }




    public static long restoreBackupForDeletion(Context context, int id, String heading, String data, int enc, String color){
        SQLiteOpenHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.TableColumn._ID, id);
        cv.put(DatabaseContract.TableColumn.HEADING, heading);
        cv.put(DatabaseContract.TableColumn.DATA, data);
        cv.put(DatabaseContract.TableColumn.ENCRYPTION_TOKEN, enc);
        cv.put(DatabaseContract.TableColumn.COLOR, color);
        long l = db.insert(DatabaseContract.TableColumn.TABLE_NAME, null, cv);
        db.close();
        return l;
    }
}
