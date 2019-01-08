package testapp.com.testappdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper{

    private static final String TAG = "DbMatrimonial";
    
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "matrimonial.db";

    private static DbHelper dbInstance;

    Context context;

    // Matrimonial Detail Table Columns names
    private static final String TABLE_MATRIMONIAL = "table_matrimonial";
    private static final String COLUMN_FIRST_NAME= "first_name";
    private static final String COLUMN_MIDDLE_NAME = "middle_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL_ID = "email_id";
    private static final String COLUMN_PHONE_NO= "phone_no";
    private static final String COLUMN_CELL_NO= "cell_no";
    private static final String COLUMN_PICTURE= "picture";
    private static final String COLUMN_AGE= "age";
    private static final String COLUMN_STREET= "street";
    private static final String COLUMN_CITY= "city";
    private static final String COLUMN_STATE= "state";
    private static final String COLUMN_GENDER= "gender";


    // create table sql query
    private String CREATE_MATRIMONIAL_TABLE = "CREATE TABLE " + TABLE_MATRIMONIAL + "("+ COLUMN_FIRST_NAME + " TEXT," + COLUMN_MIDDLE_NAME + " TEXT," + COLUMN_LAST_NAME + " TEXT," + COLUMN_EMAIL_ID + " TEXT," + COLUMN_PHONE_NO + " TEXT," + COLUMN_PICTURE + " TEXT," + COLUMN_CELL_NO + " TEXT," + COLUMN_AGE + " TEXT," + COLUMN_STREET + " TEXT," + COLUMN_CITY + " TEXT,"
            + COLUMN_STATE  + " TEXT,"+ COLUMN_GENDER  + " TEXT)";

    // drop table sql query
    private String DROP_MATRIMONIAL_TABLE = "DROP TABLE IF EXISTS " + TABLE_MATRIMONIAL;




    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_MATRIMONIAL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL(DROP_MATRIMONIAL_TABLE);
        onCreate(db);
    }


    static public synchronized DbHelper getDbInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DbHelper(context);
        }
        return dbInstance;
    }


}
