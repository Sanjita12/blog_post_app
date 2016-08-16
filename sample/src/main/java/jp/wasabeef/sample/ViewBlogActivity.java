package jp.wasabeef.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.sample.adapter.DatabaseAdapter;
import utils.Blog;

public class ViewBlogActivity extends AppCompatActivity {
    private ListView lstView;
    private DatabaseAdapter adapter;
    private ArrayAdapter<String> mAdapetr;
    private static final String TAG = CreateBlogActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_blog_acitivity);
        lstView = (ListView)findViewById(R.id.lstView);
        adapter = new DatabaseAdapter(this);
        adapter.open();
        final List<Blog> blogs = adapter.getAllBlog();
        for (Blog no : blogs) {
            Log.i(TAG, no.getBlog());
        }
        adapter.close();
        mAdapetr = new ArrayAdapter<String>(ViewBlogActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, getBlogsOnly(blogs));
        lstView.setAdapter(mAdapetr);
        lstView.setVisibility(View.VISIBLE);


    }
    private ArrayList<String> getBlogsOnly(List<Blog> blogs) {
        ArrayList<String> blogString = new ArrayList<String>();
        for (int i = 0; i < blogs.size(); i++) {
            blogString.add( blogs.get(i).getId() + ". " + blogs.get(i).getBlog());
        }

        return blogString;
    }
}
