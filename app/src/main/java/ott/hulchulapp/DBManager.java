package ott.hulchulapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class DBManager {

    private ott.hulchulapp.DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ott.hulchulapp.DatabaseHelper.SUBJECT, name);
        contentValue.put(ott.hulchulapp.DatabaseHelper.DESC, desc);
        database.insert(ott.hulchulapp.DatabaseHelper.TABLE_NAME, null, contentValue);
    }

   /* public Cursor fetch() {
        String[] columns = new String[] { ott.hulchulapp.DatabaseHelper._ID, ott.hulchulapp.DatabaseHelper.SUBJECT, ott.hulchulapp.DatabaseHelper.DESC };
        Cursor cursor = database.query(ott.hulchulapp.DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
*/

 /*
    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ott.hulchulapp.DatabaseHelper.SUBJECT, name);
        contentValues.put(ott.hulchulapp.DatabaseHelper.DESC, desc);
        int i = database.update(ott.hulchulapp.DatabaseHelper.TABLE_NAME, contentValues, ott.hulchulapp.DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
*/
   /* public void delete(long _id) {
        database.delete(ott.hulchulapp.DatabaseHelper.TABLE_NAME, ott.hulchulapp.DatabaseHelper._ID + "=" + _id, null);
    }
*/
}
