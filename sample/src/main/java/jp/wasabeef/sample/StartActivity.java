package jp.wasabeef.sample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button createBlog, viewBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_create);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.btn_view);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_create : {
                Intent intent = new Intent(getApplicationContext(),CreateBlogActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_view : {
                Intent intent = new Intent(getApplicationContext(),ViewBlogActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
