package com.example.gdziu.calmaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {
    private static final int
            DATABASE_VERSION = 1;
    public MySQLite(Context context) {
        super(context, "wydarzeniaDB",
                null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase
                                 database) {
        String DATABASE_CREATE =
                "create table wydarzenia " +
                        "(_id integer primary key autoincrement," +
                        "summary text not null," +
                        "location text not null," +
                        "startDate real not null);";
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS wydarzenia");
        onCreate(db);
    }
    public void dodaj(Wydarzenie wydarzenie){

        SQLiteDatabase db =
                this.getWritableDatabase();
        Cursor cursor =
                db.query("wydarzenia",
                        new String[] { "_id",
                                "summary", "location", "startDate" },

                        "summary = ? and location = ? and startDate = ?",
                        new String[] {
                                wydarzenie.getSummary(), wydarzenie.getLocation(), wydarzenie.getStartDate() },
                        null,
                        null,
                        null,
                        null);
        if(cursor.getCount() <= 0) {
            ContentValues values = new ContentValues();
            values.put("summary", wydarzenie.getSummary());
            values.put("location", wydarzenie.getLocation());
            values.put("startDate", wydarzenie.getStartDate());

            db.insert("wydarzenia", null, values);
        }
        db.close();
    }
    public void usun(String id) {
        SQLiteDatabase db =
                this.getWritableDatabase();
        db.delete("wydarzenia", "_id = ?",
                new String[] { id });
        db.close();
    }
    public int aktualizuj(Wydarzenie wydarzenie) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("summary", wydarzenie.getSummary());
        values.put("location", wydarzenie.getLocation());
        values.put("startDate", wydarzenie.getStartDate());

        int i = db.update("wydarzenia", values, "_id = ?", new String[]{String.valueOf(wydarzenie.getId())});
        db.close();

        return i;
    }
    public Wydarzenie pobierz(int id){

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.query("wydarzenia",
                        new String[] { "_id",
                                "summary", "location", "startDate" },

                        "_id = ?",
                        new String[] {
                                String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

        Wydarzenie wydarzenie = new
        Wydarzenie(cursor.getString(1), cursor.getString(2),
                cursor.getString(3));

        wydarzenie.setId(Integer.parseInt(cursor.getString(0))
        );

        return wydarzenie;
    }
    public Cursor lista(String warunek){
        String warunekStart = warunek + "T00:00:00.000+02:00";
        String warunekEnd = warunek + "T23:59:59.000+02:00";
        SQLiteDatabase db =
                this.getReadableDatabase();
        //return db.rawQuery("Select * from wydarzenia",null);
        return db.rawQuery("Select * from wydarzenia where startDate between ? and ?", new String[] {warunekStart, warunekEnd});
    }
}
