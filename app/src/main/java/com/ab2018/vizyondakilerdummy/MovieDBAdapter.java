package com.ab2018.vizyondakilerdummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by neval on 29/01/2018.
 */

public class MovieDBAdapter {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private final Context context;

    public static final String KEY_MOVIEID = "id";
    public static final String KEY_MOVIENAME = "name";
    public static final String KEY_MOVIEOVERVIEW = "overview";
    public static final String KEY_MOVIEIMAGE= "imagecode";
    public static final String KEY_MOVIELANGUAGE = "language";
    public static final String KEY_MOVIEVOTE = "vote";

    public static final String KEY_USERID = "id";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_USEREMAIL = "email";
    public static final String KEY_USERPASS = "password";

    public static final String KEY_RESID= "id";
    public static final String KEY_RESDATE = "date";
    public static final String KEY_RESUSERID = "userid";
    public static final String KEY_RESMOVIEID = "movieid";
    public static final String KEY_RESSEAT = "seat";

    private static final String TAG = "MovieDBAdapter";

    private static final String DATABASE_NAME = "MovieDB";
    private static final String DATABASE_MOVIETABLE = "Movies";
    private static final String DATABASE_USERTABLE = "User";
    private static final String DATABASE_RESTABLE = "Reservation";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table movies (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name text, overview text, imagecode numeric,language text,vote real);";

    // Constructor
    public MovieDBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    // To create and upgrade a database in an Android application SQLiteOpenHelper subclass is usually created
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // onCreate() is called by the framework, if the database does not exist
            Log.d("Create", "Creating the database");

            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Sends a Warn log message
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            // Method to execute an SQL statement directly
            db.execSQL("DROP TABLE IF EXISTS movies");
            onCreate(db);
        }
    }

    // Opens the database
    public MovieDBAdapter open() throws SQLException {
        // Create and/or open a database that will be used for reading only
        db = DBHelper.getReadableDatabase();
        Log.d("log","open");
        return this;

    }

    // Closes the database
    public void close() {
        // Closes the database
        DBHelper.close();
    }

    // Retrieves all the movies
    public Cursor getAllMovies() {
        Log.d("log","getall");
        return db.query(DATABASE_MOVIETABLE, new String[] { KEY_MOVIEID, KEY_MOVIENAME,
                KEY_MOVIEOVERVIEW, KEY_MOVIEIMAGE,KEY_MOVIELANGUAGE,KEY_MOVIEVOTE }, null, null, null, null, null);
    }

    // Retrieves user info
    public Cursor getUser(String email,String pass) {
        return db.query(DATABASE_USERTABLE, new String[] {KEY_USERID,KEY_USERNAME}, KEY_USEREMAIL+ "='" + email +"' and '" +KEY_USERPASS + "='"+pass+"'", null, null, null, null);
    }

    // Insert a reservation into the database
    public long insertReservation(String date, int movieid, int userid, String seat) {
        // The class ContentValues allows to define key/values. The "key" represents the
        // table column identifier and the "value" represents the content for the table
        // record in this column. ContentValues can be used for inserts and updates of database entries.
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_RESDATE, date);
        initialValues.put(KEY_RESMOVIEID, movieid);
        initialValues.put(KEY_RESUSERID, userid);
        initialValues.put(KEY_RESSEAT, seat);
        return db.insert(DATABASE_RESTABLE, null, initialValues);
    }

}
