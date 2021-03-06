package com.example.godoctor.myapplication1.newApp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;
import com.example.godoctor.myapplication1.newApp.models.Person;
import com.example.godoctor.myapplication1.newApp.activities.DetailsActivity;
import com.example.godoctor.myapplication1.newApp.adapters.ListAdapter;

import java.util.ArrayList;


public class FamilyFragment extends Fragment {


    ArrayList<Person> person=new ArrayList<>();
    String frag=" ";

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        frag=args.getString("message");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        person=new DbHelper(getActivity()).getAllFamilyAndFriends(frag);
//        Log.e("family",person.get(0).category);

        View v= inflater.inflate(R.layout.fragment_family, container, false);
        ListView lv= (ListView) v.findViewById(R.id.listView);
        lv.setLongClickable(true);
        if(person.size()==0){
            TextView tv= (TextView) v.findViewById(R.id.warning);
            tv.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }else{
            registerForContextMenu(lv);
            lv.setAdapter(new ListAdapter(getActivity(),person));

        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(getActivity(),DetailsActivity.class);
                in.putExtra("name",person.get(position).getName());
                in.putExtra("des",person.get(position).getDescription());
                startActivity(in);
            }

        });

        return v;
    }

}
