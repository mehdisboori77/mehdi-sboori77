package com.example.myapplication1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    String TABLE_NAME;
    String _ID;
    String NAME;
    String EMAIL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(this,null,1,null) {
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    NAME + " TEXT," +
                    EMAIL + " TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    };

    private void insert(String Name, String Email) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, Name);
        values.put(EMAIL, Email);
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    private void delete(String Name) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String selection = NAME + " LIKE ?";
        String[] selectionArgs = {Name};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
    }

}
