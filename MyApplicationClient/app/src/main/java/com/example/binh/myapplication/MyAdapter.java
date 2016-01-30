package com.example.binh.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by binh on 1/11/2016.
 */
public class MyAdapter extends ArrayAdapter<Person> {
    private Activity context;
    private int layoutID;
    private ArrayList<Person> data;

    public MyAdapter(Activity context, int layoutID, ArrayList<Person> data){
        super(context, layoutID, data);

        this.context = context;
        this.layoutID = layoutID;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(layoutID, null);

            holder = new ViewHolder(row);

            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        holder.populateFrom(data.get(position));

        return row;
    }

    class ViewHolder{
        private TextView tvName, tvAge;

        public ViewHolder(View row){
            tvName = (TextView) row.findViewById(R.id.tvName);
            tvAge = (TextView) row.findViewById(R.id.tvAge);
        }

        public void populateFrom(Person p){
            tvName.setText(p.getName());
            tvAge.setText(p.getAge());
        }
    }
}