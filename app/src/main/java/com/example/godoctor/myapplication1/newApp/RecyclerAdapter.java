package com.example.godoctor.myapplication1.newApp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.godoctor.myapplication1.R;

import java.util.ArrayList;

/**
 * Created by godoctor on 23/1/17.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Person> arrayList;
    Context con;

    RecyclerAdapter(ArrayList<Person> arr, RecyclerViewActivity recyclerViewActivity) {
       arrayList =new ArrayList<>();
        arrayList=arr;
        Log.e("size==",arr.size()+"");
        con=recyclerViewActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.first.setText(arrayList.get(position).getName());
        holder.last.setText(arrayList.get(position).getCategory());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(con, NewMainActivity.class);
                con.startActivity(in);
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView first, last;
        Button btn;

        MyViewHolder(View view) {
            super(view);
            first= (TextView) view.findViewById(R.id.firstLine);
            last= (TextView) view.findViewById(R.id.secondLine);
            btn= (Button) view.findViewById(R.id.edit);

        }
    }
}
