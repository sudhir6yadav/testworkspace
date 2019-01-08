package testapp.com.testappdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import testapp.com.testappdemo.models.MaterimonialDetailModel;

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


    ////instance of database
    static public synchronized DbHelper getDbInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DbHelper(context);
        }
        return dbInstance;
    }


    ///insert detail into db
    public void addDetail(List<MaterimonialDetailModel> materimonialDetailModels) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        for(int i=0;i<materimonialDetailModels.size();i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FIRST_NAME, materimonialDetailModels.get(i).getFirstname());
            values.put(COLUMN_LAST_NAME, materimonialDetailModels.get(i).getLastname());
            values.put(COLUMN_MIDDLE_NAME, materimonialDetailModels.get(i).getMiddlename());
            values.put(COLUMN_GENDER, materimonialDetailModels.get(i).getGender());
            values.put(COLUMN_EMAIL_ID, materimonialDetailModels.get(i).getEmail());
            values.put(COLUMN_AGE, materimonialDetailModels.get(i).getAge());
            values.put(COLUMN_PHONE_NO, materimonialDetailModels.get(i).getPhoneno());
            values.put(COLUMN_CELL_NO, materimonialDetailModels.get(i).getCellno());
            values.put(COLUMN_PICTURE, materimonialDetailModels.get(i).getPicture());
            values.put(COLUMN_CITY, materimonialDetailModels.get(i).getCity());
            values.put(COLUMN_STATE, materimonialDetailModels.get(i).getState());
            values.put(COLUMN_STREET, materimonialDetailModels.get(i).getStreet());


            // Inserting Row
            long insertResult = db.insert(TABLE_MATRIMONIAL, null, values);

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }


    ////fetch all list
    public List<MaterimonialDetailModel> showDetails() {


        String sql = "select * from "+TABLE_MATRIMONIAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<MaterimonialDetailModel> materimonialDetailModels=new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                MaterimonialDetailModel materimonialDetailModel=new MaterimonialDetailModel();
                materimonialDetailModel.setFirstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                materimonialDetailModel.setMiddlename(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLE_NAME)));
                materimonialDetailModel.setLastname(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
                materimonialDetailModel.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                materimonialDetailModel.setCellno(cursor.getString(cursor.getColumnIndex(COLUMN_CELL_NO)));
                materimonialDetailModel.setPhoneno(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NO)));
                materimonialDetailModel.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
                materimonialDetailModel.setState(cursor.getString(cursor.getColumnIndex(COLUMN_STATE)));
                materimonialDetailModel.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL_ID)));
                materimonialDetailModel.setStreet(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)));
                materimonialDetailModel.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                materimonialDetailModel.setPicture(cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE)));
                materimonialDetailModels.add(materimonialDetailModel);

            }while (cursor.moveToNext());

            cursor.close();
            return materimonialDetailModels;

        }
        cursor.close();
        return null;

    }

    ///delete all data
    public void deleteAllDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete  from " + TABLE_MATRIMONIAL);
        db.close();
    }
}
