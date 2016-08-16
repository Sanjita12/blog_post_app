package jp.wasabeef.sample.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.sample.R;
import utils.Blog;

public class DatabaseAdapter  {
    private SQLiteDatabase db;
    private MyDbHelper dbHelper;
    private static final String DB_NAME = "mydatabase.sqlite";
    private static final int DB_VERSION = 1;
    private static final String BLOG_TABLE = "blog";
    private static final String blog = "blogs";

    private Context mContext;
    String columns[] = {"_id", blog};

    private static final String CREATE_BLOG_TABLE = "CREATE TABLE " + BLOG_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + blog + " TEXT);";


    public DatabaseAdapter(Context context) {
        this.mContext = context;
        dbHelper = new MyDbHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }


    public void close() {
        db.close();

    }

    public void insertBlog(Blog blog1){
        ContentValues cv = new ContentValues();
        cv.put(blog, blog1.getBlog());
        int id = (int) db.insert(BLOG_TABLE,null,cv);
        if (id>0){
            Toast.makeText(mContext,"Blog is posted",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(mContext,"Please write a blog",Toast.LENGTH_LONG).show();
        }
    }

    public List<Blog> getAllBlog() {
        List<Blog> blogs = new ArrayList<Blog>();
        Cursor cursor = db.query(BLOG_TABLE, columns, null, null, null, null, null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + NOTE_TABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Blog n = new Blog();
            n.setId(cursor.getInt(0));
            n.setBlog(cursor.getString(1));
            blogs.add(n);
            cursor.moveToNext();
        }
        return blogs;

    }

    public Blog getOneNote(int id) {
        Blog blog = new Blog();
        Cursor cursor = db.query(BLOG_TABLE, columns, "_id =" + id, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            blog.setBlog(cursor.getString(0));
            cursor.moveToNext();

        }

        return blog;

    }


    private class MyDbHelper extends SQLiteOpenHelper {
        private MyDbHelper(Context context, String notes, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, notes, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_BLOG_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            db.execSQL("DROP TABLE IF EXISTS table_name");
            onCreate(db);
        }


    }


}
