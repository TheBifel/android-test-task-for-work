package com.bifel.testtaskforwork.screens.tab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public final class ContactRepositorySQL implements ContactRepository {

    DBHelper dbHelper;
    SQLiteDatabase db;

    private static final String TABLE_NAME = "contacts_table";

    ContactRepositorySQL(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void saveRecords(ContactList contactList) {
        ContentValues cv = new ContentValues();

        for (Contact contact : contactList.getContactsToAdd()) {
            cv.put("name", contact.getName());
            contact.setSqlId(db.insert(TABLE_NAME, null, cv));
        }

        for (Contact contact : contactList.getContactsToRemove()) {
            db.delete(TABLE_NAME, "id=" + contact.getSqlId() , null);
        }

        contactList.clearData();
    }

    @Override
    public List<Contact> getRecords() {
        List<Contact> list = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            do {
                list.add(new Contact(c.getString(nameColIndex), c.getInt(idColIndex)));
            } while (c.moveToNext());
        } else {
            c.close();
        }
        return list;
    }

    public void close() {
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " ("
                    + "id integer primary key autoincrement,"
                    + "name text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
