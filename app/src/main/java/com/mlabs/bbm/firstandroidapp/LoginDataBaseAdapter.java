package com.mlabs.bbm.firstandroidapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "user.db";
    static final int DATABASE_VERSION = 1;
    public static final String NAME_COLUMN = "user";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "LOGIN" +
            "( " + "ID" + " integer primary key autoincrement," + "USERNAME  text,PASSWORD text,FIRSTNAME text,LASTNAME text,USERNAME2 text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

//    public void insertEntry(String userName, String password,String created_at) {
//        ContentValues newValues = new ContentValues();
//        // Assign values for each row.
//        newValues.put("USERNAME", userName);
//        newValues.put("PASSWORD", password);
//        newValues.put("CREATED_AT", created_at);
//    public void insertEntry(String userName, String password) {
    public void registeruser(String em, String psword,String fname, String lname,String usname) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", em);
        newValues.put("PASSWORD", psword);
        newValues.put("FIRSTNAME", fname);
        newValues.put("LASTNAME", lname);
        newValues.put("USERNAME2", usname);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }


//    public int deleteEntry(String em) {
//        //String id=String.valueOf(ID);
//        String where = "USERNAME=?";
//        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{em});
//        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
//        return numberOFEntriesDeleted;
//    }

    public String getSinlgeEntry(String em) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{em}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();

//ETOO YES WAT
            Cursor cursor2 = db.query("LOGIN", null, " USERNAME2=?", new String[]{em}, null, null, null);
            if (cursor2.getCount() < 1) // UserName Not Exist
            {
                cursor2.close();
                return "NOT EXIST";
            }

            cursor2.moveToFirst();
            String password2 = cursor2.getString(cursor2.getColumnIndex("PASSWORD"));
            cursor2.close();
            return password2;
//ETOO 2YES WAT ======================>

        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


    public boolean validateExisting(String em) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{em}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();

//ETOO YES WAT
            Cursor cursor2 = db.query("LOGIN", null, " USERNAME2=?", new String[]{em}, null, null, null);
            if (cursor2.getCount()< 1) // UserName Not Exist
            {
                cursor2.close();
                return false;
                //not exist
            }

            cursor2.moveToFirst();
            String password2 = cursor2.getString(cursor2.getColumnIndex("PASSWORD"));
            cursor2.close();
            return true;
//ETOO 2YES WAT ======================>

        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return true;
    }

//
//    public String getSinlgeEntry(String em) {
//        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{em}, null, null, null);
//        if (cursor.getCount() < 1) // UserName Not Exist
//        {
//            cursor.close();
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
//        cursor.close();
//        return password;
//    }


//    public void updateEntry(String em, String psword,String fname, String lname,String usname) {
//        // Define the updated row content.
//        ContentValues updatedValues = new ContentValues();
//        // Assign values for each row.
//        updatedValues.put("USERNAME", em);
//        updatedValues.put("PASSWORD", psword);
//        updatedValues.put("FIRSTNAME", fname);
//        updatedValues.put("LASTNAME", lname);
//        updatedValues.put("USERNAME2", usname);
//        String where = "USERNAME = ?";
//        db.update("LOGIN_NEW", updatedValues, where, new String[]{em});
//    }
}
