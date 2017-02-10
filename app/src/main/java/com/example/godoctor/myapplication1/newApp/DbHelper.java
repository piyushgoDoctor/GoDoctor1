package com.example.godoctor.myapplication1.newApp;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.example.godoctor.myapplication1.newApp.models.Page;
import com.example.godoctor.myapplication1.newApp.models.Person;
import com.example.godoctor.myapplication1.newApp.models.Signup;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String PERSONS_TABLE_NAME = "Persons";
    public static final String PERSONS_COLUMN_ID = "id";
    public static final String PERSONS_COLUMN_NAME = "name";
    public static final String PERSONS_COLUMN_DES = "des";
    public static final String PERSONS_COLUMN_REL = "rel";
    public static final String PERSONS_COLUMN_CAT = "category";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Persons " +
                        "(id integer primary key, name text,des text,rel text, category text)");
        db.execSQL(
                "create table Signup " +
                        "(id integer primary key, name text,des text,imageUrl text)");
        db.execSQL(
                "create table page " +
                        "(id integer primary key, person text,share text,summary text,image blob)");

    }
    public boolean insertPage (String person,  String share, String summary, byte[] imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("person", person);
        contentValues.put("share", share);
        contentValues.put("summary", summary);
        contentValues.put("image", imageUrl);

        db.insert("page", null, contentValues);
        return true;
    }
    public ArrayList<Page> getAllPage() {
        ArrayList<Page> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from page", null );
        res.moveToFirst();


        while(res.isAfterLast() == false){
            byte[] img=res.getBlob(res.getColumnIndex("image"));
            array_list.add(new Page(res.getString(res.getColumnIndex("id")), res.getString(res.getColumnIndex("person")),
                    res.getString(res.getColumnIndex("share")),res.getString(res.getColumnIndex("summary")),img));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Signup> getAllSignup() {
        ArrayList<Signup> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Signup", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Signup(res.getString(res.getColumnIndex("id")), res.getString(res.getColumnIndex("name")),
                    res.getString(res.getColumnIndex("des")),res.getString(res.getColumnIndex("imageUrl"))));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean insertSignup (String name,  String des, String imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("des", des);
        contentValues.put("imageUrl", imageUrl);

        db.insert("Signup", null, contentValues);
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Persons");
        onCreate(db);
    }

    public boolean insertPersons (String name,  String des, String rel, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("des", des);
        contentValues.put("rel", rel);
        contentValues.put("category", category);
        db.insert("Persons", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Persons where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PERSONS_TABLE_NAME);
        return numRows;
    }

    public boolean updatePersons (Integer id, String name, String des, String rel, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("des", des);
        contentValues.put("rel", rel);
        contentValues.put("category", category);

        db.update("Persons", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePersons (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Persons",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Persons", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Person(res.getString(res.getColumnIndex(PERSONS_COLUMN_NAME)), res.getString(res.getColumnIndex(PERSONS_COLUMN_DES)),
                    res.getString(res.getColumnIndex(PERSONS_COLUMN_REL)),res.getString(res.getColumnIndex(PERSONS_COLUMN_CAT)),res.getString(res.getColumnIndex(PERSONS_COLUMN_ID))));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getAllPersonsName() {
        ArrayList<String> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select name from Persons", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PERSONS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Person> getAllFamilyAndFriends(String strs){
        ArrayList<Person> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Persons WHERE category = '"+strs+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Person(res.getString(res.getColumnIndex(PERSONS_COLUMN_NAME)), res.getString(res.getColumnIndex(PERSONS_COLUMN_DES)),
                    res.getString(res.getColumnIndex(PERSONS_COLUMN_REL)),res.getString(res.getColumnIndex(PERSONS_COLUMN_CAT)),res.getString(res.getColumnIndex(PERSONS_COLUMN_ID))));
            res.moveToNext();
        }
        return array_list;



    }

}