package com.example.godoctor.myapplication1.newApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;
import com.example.godoctor.myapplication1.newApp.models.Person;
import com.example.godoctor.myapplication1.newApp.activities.DetailsActivity;

import java.util.ArrayList;

/**
 * Created by godoctor on 23/1/17.
 */

public class ListAdapter extends ArrayAdapter<Person> {
    Context con;
    ArrayList<Person> person=new ArrayList<>();


    public ListAdapter(Context context, ArrayList<Person> resource) {
        super(context, R.layout.list_adapter_layout);
        con=context;
        person=resource;
    }
    @Override
    public int getCount() {
        return person.size();
    }
    @Override
    public Person getItem(int position) {
        // TODO Auto-generated method stub
        return person.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_layout, parent, false);

        Holder holder=new Holder();
        holder.firstLine= (TextView) convertView.findViewById(R.id.firstLine);
        holder.secondLine= (TextView) convertView.findViewById(R.id.secondLine);
        holder.delete= (Button) convertView.findViewById(R.id.delete);
        holder.edit= (Button) convertView.findViewById(R.id.edit);
        holder.firstLine.setText(person.get(position).getName());
        holder.secondLine.setText(person.get(position).getCategory());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new DbHelper(con).deletePersons(Integer.parseInt(person.get(position).getId()));
            }
        });


      convertView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
              Toast.makeText(con, "Long Clicked Trigger: ", Toast.LENGTH_LONG).show();
              Dialog();
              return false;
          }
      });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(con,DetailsActivity.class);
                in.putExtra("name",person.get(position).getName());
                in.putExtra("des",person.get(position).getDescription());
                con.startActivity(in);
            }
        });
        return convertView;

    }

    private static class Holder{
        TextView firstLine,secondLine;
        Button delete,edit;
    }
     void Dialog()    {

        AlertDialog.Builder dialogAlert = new AlertDialog.Builder(con);
        dialogAlert.setTitle("Demo \n\n\n Edit\n\nDelete");
        dialogAlert.show();
        dialogAlert.setCancelable(true);
    }
}
