package com.example.windows7.itonatalagalordthankyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by windows7 on 3/6/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG= "DatabaseHelper";
    private static final String TABLE_STUDENTS="student_table";
    private static final String ID="ID";
    private static final String STUD_ID="stud_id";
    private static final String FNAME="first_name";
    private static final String LNAME="last_name";
    private static final String MNAME="middle_name";
    private static final String PASSWORD="password";
    private static final String PARENT="parent";
    //SUBJECTS
    private static final String ENGLISH="english";
    private static final String MATH="math";
    private static final String FILIPINO="filipino";
    private static final String SOCIALSCI="social_sci";
    private static final String SCITECH="sci_tech";


    public DatabaseHelper (Context context){
        super(context, TABLE_STUDENTS, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableforStudent="CREATE TABLE student_table (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " stud_id TEXT," +
                "last_name TEXT, first_name TEXT, middle_name TEXT, password TEXT, parent TEXT, " +
                "english TEXT, math TEXT, filipino TEXT, social_sci TEXT, sci_tech TEXT ) ";
        db.execSQL(createTableforStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_STUDENTS));
        onCreate(db);

    }
    public boolean addStudent(int studID, String lname, String fname, String mname, String pass, String parents){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(STUD_ID, studID);//1
        contentValues.put(LNAME, lname);//2
        contentValues.put(FNAME,fname);//3
        contentValues.put(MNAME,mname);//4
        contentValues.put(PASSWORD, pass);
        contentValues.put(PARENT, parents);
        contentValues.put(ENGLISH,"60");
        contentValues.put(FILIPINO, "70");
        contentValues.put(MATH, "60");
        contentValues.put(SCITECH, "60");
        contentValues.put(SOCIALSCI, "70");
        Log.d(TAG, "addData: Adding "+studID+","+lname+","+fname+","+mname+","+pass+","
                +parents+ " to "+TABLE_STUDENTS);
        long result =db.insert(TABLE_STUDENTS,null, contentValues);


        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getAllStudent(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM " + TABLE_STUDENTS;
        Cursor data= db.rawQuery(query, null);
        return  data;

    }
    public Cursor getItemID(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        String query="SELECT " +ID+","+LNAME+","+FNAME+","+MNAME+","+ENGLISH+","+FILIPINO+","+MATH+","+
                SCITECH+","+SOCIALSCI+","+PASSWORD+","+STUD_ID+","+PARENT+
                " FROM "+TABLE_STUDENTS+" WHERE " +STUD_ID + "= '" + name + "'";
        Cursor data= db.rawQuery(query, null);
        return data;
    }
    public Cursor getID (String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String query= "SELECT last_name, first_name, middle_name FROM "+TABLE_STUDENTS+
                " WHERE "+STUD_ID+"= '"+id+"'";

        Cursor data= db.rawQuery(query, null);
        return data;
    }

    public ArrayList<Object>getUser(long id){
        ArrayList<Object> rowArray= new ArrayList<Object>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        try{
            cursor=db.query(
                    TABLE_STUDENTS, new String [] {ID,LNAME, FNAME, MNAME },ID+ "=" + id,
                    null, null,null, null, null) ;
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do {
                    rowArray.add(cursor.getLong(0));
                    rowArray.add(cursor.getString(1));
                    rowArray.add(cursor.getString(2));
                    rowArray.add(cursor.getString(3));
                }while (cursor.moveToNext());

            }cursor.close();
        }
        catch (SQLException e){
            Log.e ("DB ERROR: GET ID", e.toString() );
            e.printStackTrace();
        }
        return rowArray;

    }

    public boolean updateData(String id, String english, String filipino, String math, String social,String science){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ENGLISH, english);
        contentValues.put(FILIPINO, filipino);
        contentValues.put(MATH, math);
        contentValues.put(SOCIALSCI, social);
        contentValues.put(SCITECH, science);

        db.update(TABLE_STUDENTS, contentValues, "stud_id= ?", new String[] {id});
        return true;
    }

    public boolean updateStudent(String id, String lname, String fname, String mname, String pass, String parents){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(STUD_ID, id);//1
        contentValues.put(LNAME, lname);//2
        contentValues.put(FNAME,fname);//3
        contentValues.put(MNAME,mname);//4
        contentValues.put(PASSWORD, pass);
        contentValues.put(PARENT, parents);



        db.update(TABLE_STUDENTS, contentValues, "stud_id= ?", new String[] {id});
        return true;
    }


    public void updateGrades(int studentid, int eng, int fil, int math, int sci, int social){
        SQLiteDatabase db= this.getWritableDatabase();
        String query = "UPDATE "+TABLE_STUDENTS
                +" SET "+ ENGLISH+" ="+eng+" ,"
                +FILIPINO+" ="+fil+" ,"
                +MATH+" ="+math+" ,"
                +SCITECH+" ="+sci+" ,"
                +SOCIALSCI+" ="+social+" WHERE stud_id ="+studentid;
        Log.d(TAG, "updateName: query: " + query);

        db.execSQL(query);

    }
    public void deleteName(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_STUDENTS + " WHERE "
                + STUD_ID + " = '" + id+"'";
        Log.d(TAG, "deleteName: query: " + query);

        db.execSQL(query);
    }



}
