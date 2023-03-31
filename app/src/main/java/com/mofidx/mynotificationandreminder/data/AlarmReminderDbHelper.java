package com.mofidx.mynotificationandreminder.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class AlarmReminderDbHelper extends SQLiteOpenHelper {
    String desc;

    private static final String DATABASE_NAME = "alarmreminder.db";

    private static final int DATABASE_VERSION = 1;

    public AlarmReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the reminder table
        String SQL_CREATE_ALARM_TABLE =  "CREATE TABLE " + AlarmReminderContract.AlarmReminderEntry.TABLE_NAME + " ("
                + AlarmReminderContract.AlarmReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TITLE + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_DATE + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_TIME + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE + " TEXT NOT NULL, "
                + AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE + " TEXT NOT NULL " + " );";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_ALARM_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @SuppressLint("Range")
    public String getDescmodel(String modelSearch){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+AlarmReminderContract.AlarmReminderEntry.TABLE_NAME+" WHERE "+AlarmReminderContract.AlarmReminderEntry.KEY_TITLE+" LIKE ?",new String[]{"%"+modelSearch+"%"});
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+CAR_TB_NAME+" WHERE "+CAR_CLMN_MODEL+"=?",new String[]{modelSearch});
        // كود التعامل مع الكيرسر وتحويله لمصفوفة من نوع كار
        // فحص هل الكيرسر يحتوي على بيانات في الصف التالي ام لا
        if (cursor!= null && cursor.moveToFirst()){
            do {
                // البحث عن العامود على حسب اسم العامود cursor.getColumnIndex()
//                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry._ID));
                       desc = cursor.getString(cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE));


            }while (cursor.moveToNext());
            cursor.close();
        }

        return desc;
    }



}
