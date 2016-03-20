package wqyap762.rprqs;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "RPRQS.db";
    public static final String TABLE_USER = "User";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_HPNO = "hpno";

    //private static final int COL_ID = 0;
    //private static final int COL_USERNAME = 0;
    //private static final int COL_FULLNAME = 1;
    //private static final int COL_PASSWORD = 2;
    //private static final int COL_HPNO = 3;

    SQLiteDatabase db;
    private static final String CREATE_TABLE = "create table User (id integer primary key autoincrement, " +
            "username text not null, fullname text not null, password text not null, hpno integer not null);";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;
    }

    // register user
    public void createUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        /*String query = "SELECT * FROM User";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);*/
        values.put(COLUMN_FULLNAME, user.get_fullname());
        values.put(COLUMN_USERNAME, user.get_username());
        values.put(COLUMN_PASSWORD, user.get_password());
        values.put(COLUMN_HPNO, user.get_hpno());

        db.insert(TABLE_USER, null, values);
    }

    // search for username availability
    public String searchUsername(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);
        String a = "available";
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(username)) {
                    a = cursor.getString(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return a;
    }

    // match username and password
    public String searchPassword(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, password FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);
        String b = "not found";
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_USER;
        db.execSQL(query);
        this.onCreate(db);
    }
}
