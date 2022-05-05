package com.example.kcc_multiplex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MoiveListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Movies> movielist;

    public MoiveListAdapter(Context context, int layout, ArrayList<Movies> movielist) {
        this.context = context;
        this.layout = layout;
        this.movielist = movielist;
    }

    @Override
    public int getCount() {
        return movielist.size();
    }

    @Override
    public Object getItem(int position) {
        return movielist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView movieimage;
        TextView mName;


    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row =view;
        ViewHolder holder= new ViewHolder();

        if(row == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =inflater.inflate(layout,null);

            holder.mName=(TextView) row.findViewById(R.id.mName);
            holder.movieimage=(ImageView) row.findViewById(R.id.imgMovies);
            row.setTag(holder);
        }

        else{
            holder=(ViewHolder) row.getTag();

        }

        Movies movie= movielist.get(position);

        holder.mName.setText(movie.getMovivename());

        byte[] movieimage=movie.getMovieimage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(movieimage,0,movieimage.length);
        holder.movieimage.setImageBitmap(bitmap);




        return row;
    }
}
