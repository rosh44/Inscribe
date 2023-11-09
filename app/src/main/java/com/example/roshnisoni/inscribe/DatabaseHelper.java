package com.example.roshnisoni.inscribe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME2= "textnotes";
    private static final String TABLE_NAME1= "password";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_CONTENT = "content";
    private static final String DATABASE_NAME = "Inscribe";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE1 = "CREATE TABLE "+ TABLE_NAME1+ " (PASSWORD TEXT PRIMARY KEY)";
        String TABLE_CREATE2 = "CREATE TABLE "+ TABLE_NAME2+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_TITLE+" TEXT, " +COLUMN_CONTENT+ " TEXT)";
        db.execSQL(TABLE_CREATE1);
        db.execSQL(TABLE_CREATE2);
    }

    public boolean saveNotes(String title,String content){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_CONTENT,content);
        long result= db.insertOrThrow(TABLE_NAME2,null,values);
        if(result == -1)
        {return false;
        }
        else
        {return true;
        }

    }

    public void insertPassword(String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("PASSWORD",password);
        db.insertOrThrow(TABLE_NAME1,null,values);

    }
    public boolean updatePassword(String newpwd, String oldpwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("PASSWORD",newpwd);
        long result= db.update(TABLE_NAME1,values,COLUMN_PASSWORD+ "=" + oldpwd,null);
        if(result == -1)
        {return false;
        }
        else
        {return true;
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query="DROP TABLE IF EXISTS " +TABLE_NAME2;
        String query1="DROP TABLE IF EXISTS " +TABLE_NAME1;
        db.execSQL(query);
        db.execSQL(query1);
        onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+TABLE_NAME2;
        Cursor data=db.rawQuery(query,null);
        return data;
    }

    public int getPassword(){
        SQLiteDatabase db=this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_NAME1;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return icount;
    }

    public String getCorrectPassword(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select PASSWORD from "+TABLE_NAME1;
        Cursor data=db.rawQuery(query,null);
        data.moveToFirst();
        String b=data.getString(0);
        return b;
    }
    public String getContent(String title){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME2;
        Cursor data=db.rawQuery(query,null);
        String b="";
        while(data.moveToNext()){
            String a=data.getString(1);
            if(a.equals(title)){
                b=data.getString(2);
                break;}

        }
        return b;
    }

    /*public Cursor getAllContent(String title){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME;
        Cursor data=db.query(TABLE_NAME, COLUMN_CONTENT, COLUMN_TITLE "=" + title +"'", null, null, null, null);
        return data;
    }*/


    public boolean updateNotes(int id,String title,String content,String newtitle, String newcontent){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,newtitle);
        values.put(COLUMN_CONTENT,newcontent);
        long result= db.update(TABLE_NAME2,values,COLUMN_ID+ "=" + id,null);
        if(result == -1)
        {return false;
        }
        else
        {return true;
        }

    }


    public int getid(String title,String content){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME2;
        Cursor data=db.rawQuery(query,null);
        int c=0;
        while(data.moveToNext()){
            String a=data.getString(1);
            String b=data.getString(2);
            if(a.equals(title) && b.equals(content)){
                c=data.getInt(0);
                break;}

        }
        return c;
    }

    public boolean deleteNotes(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, COLUMN_ID + "=" + id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
