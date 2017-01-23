package com.example.godoctor.myapplication1.newApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.godoctor.myapplication1.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ArrayList<Person> arr=new DbHelper(RecyclerViewActivity.this).getAllPersons();

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
         LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        RecyclerAdapter mAdapter=new RecyclerAdapter(arr,RecyclerViewActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Person> arr=new DbHelper(RecyclerViewActivity.this).getAllPersons();

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        RecyclerAdapter mAdapter=new RecyclerAdapter(arr,RecyclerViewActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}

