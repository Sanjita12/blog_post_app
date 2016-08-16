package jp.wasabeef.sample.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.sample.R;
import utils.Blog;

public class BlogAdapter extends ArrayAdapter<Blog> {
    private LayoutInflater inflater;
    private ArrayList<Blog> blogs = new ArrayList<>();

    public BlogAdapter(Context context, List<Blog> objects) {
        super(context, R.layout.blog_adapter, objects);
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        blogs.addAll(objects);
    }

    @Override
    public int getCount() {
        return blogs.size();
    }

    @Override
    public Blog getItem(int position) {
        return blogs.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Blog blog = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.blog_adapter, parent, false);
            holder = new ViewHolder();
            holder.tvBlog = (TextView) convertView.findViewById(R.id.tvBlog);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvBlog.setText(Html.fromHtml(blog.getBlog()).toString());
        return convertView;
    }

    public class ViewHolder {
        TextView tvBlog;
    }


}
