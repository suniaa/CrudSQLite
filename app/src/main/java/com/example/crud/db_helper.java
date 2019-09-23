package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class db_helper extends SQLiteOpenHelper {
    public db_helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "biodata.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IDENTITAS( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS IDENTITAS;");
        onCreate(sqLiteDatabase);
    }

    public void insert_data(String nama){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA",nama);
        this.getWritableDatabase().insertOrThrow("IDENTITAS","",contentValues);
    }

    public void edit_data(String old_nama, String new_nama){
        this.getWritableDatabase().execSQL("UPDATE IDENTITAS SET NAMA='"+new_nama+"' WHERE NAMA='"+old_nama+"'");
    }

    public void delete_data(String nama){
        this.getWritableDatabase().delete("IDENTITAS","NAMA='"+nama+"'",null);
    }

    public void list_biodata(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM IDENTITAS",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+"\n");
        }
    }

}
