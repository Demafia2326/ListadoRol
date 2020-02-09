package com.proyecto.listadorol.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.proyecto.listadorol.views.MyApplication;

import java.util.ArrayList;

public class PeopleModel extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rolDatabase";
    private static final int DATABASE_VERSION = 1;

    private static PeopleModel sInstance;



    private PeopleModel(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  CREATE_TABLE_PERSON="CREATE TABLE Person(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "user_name TEXT," +
                "edad Integer," +
                "personalidad TEXT," +
                "img TEXT," +
                "fecha TEXT," +
                "raza TEXT,"  +
                "caos Integer"  +
                ");";

        db.execSQL(CREATE_TABLE_PERSON);
    }

    public boolean addNew(PersonMin person) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).


            ContentValues values = new ContentValues();
            values.put("user_name", person.getAvatar_name());
            values.put("name", person.getPlayer_name());
            values.put("edad",person.getEdad());
            values.put("raza",person.getRaza());
            values.put("personalidad",person.getPersonalidad());
            values.put("fecha", person.getFecha());
            values.put("img",person.getImagen());
            values.put("caos",person.getCaos());

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow("Person", null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            String TAG="DB";
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
            db.close();
            return true;

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static synchronized PeopleModel getInstance() {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new PeopleModel( MyApplication.getContext());
        }
        return sInstance;
    }


    public ArrayList<PersonMin> getAllPerson(){



        ArrayList<PersonMin> list = new ArrayList<PersonMin>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT img, name, user_name FROM Person", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new PersonMin(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        return list;
    }


    public ArrayList<PersonMin> BuscarPersona(String nombre2, String genero, String fecha) {

        String[] args = new String[] {nombre2, genero, fecha};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT img, name, user_name FROM Person WHERE nombre=? or raza = ? or fecha = ? UNION" +
                " SELECT img, name, user_name FROM Person WHERE name=? AND raza = ?   ", args);



        Log.d("AQUI", cursor.getCount()+"");
        ArrayList<PersonMin> list = new ArrayList<PersonMin>();
        if (cursor.moveToFirst()) {
            do {
                list.add(new PersonMin(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        Log.d("AQUI2", list.toString()+"");
        return list;

    }




    public boolean updateP(PersonMin person){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put("nombre", person.getAvatar_name());
            values.put("edad", person.getEdad());
            values.put("user_name", person.getPlayer_name());
            values.put("raza", person.getRaza());
            values.put("personalidad", person.getPersonalidad());
            values.put("fecha", person.getFecha());
            values.put("caos", person.getCaos());
            values.put("img", person.getImagen());

            String[] args = new String[]{String.valueOf(person.getId())};
            db.update("Person",values,"id=?",args);
            db.setTransactionSuccessful();
        }catch (Exception e){

            return false;
        } finally {
            db.endTransaction();
            db.close();
            return true;
        }
    }

    public boolean delete(PersonMin person){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String[] args = new String[]{String.valueOf(person.getId())};
            db.delete("Person", "id=?", args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("DB", "Error while trying to delete post to database");
            return false;
        } finally {
            db.endTransaction();
            return true;
        }
    }




}
