package com.example.godoctor.myapplication1.newApp.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.godoctor.myapplication1.R;
import com.example.godoctor.myapplication1.newApp.DbHelper;
import com.example.godoctor.myapplication1.newApp.adapters.ListAdapter;
import com.example.godoctor.myapplication1.newApp.models.Person;
import com.example.godoctor.myapplication1.newApp.models.Signup;

import java.util.ArrayList;

public class NewMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<Person> person=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent in=new Intent(NewMainActivity.this,AddNewPersonActivity.class);
                startActivity(in);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.isSelected();
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);


        ImageView profilePic= (ImageView) hView.findViewById(R.id.profilePic);
        TextView profileName= (TextView) hView.findViewById(R.id.profileName);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewMainActivity.this,SignupActivity.class));
            }
        });
        profileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewMainActivity.this,SignupActivity.class));
            }
        });


        ArrayList<Signup> profile=new DbHelper(NewMainActivity.this).getAllSignup();
        if( profile != null && profile.size()!=0) {
            profilePic.setImageBitmap(BitmapFactory.decodeFile(profile.get(0).getImageUrl()));
            profileName.setText(profile.get(0).getName());
        }

        person=new DbHelper(NewMainActivity.this).getAllPersons();

        ListView lv= (ListView) findViewById(R.id.listView);
        lv.setLongClickable(true);
        if(person.size()==0){
            TextView tv= (TextView) findViewById(R.id.warning);
            tv.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }else{
            registerForContextMenu(lv);

            lv.setAdapter(new ListAdapter(NewMainActivity.this,person));

        }

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                Toast.makeText(NewMainActivity.this, "Long Clicked Trigger: ", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NewMainActivity.this, " Clicked Trigger: ", Toast.LENGTH_LONG).show();

            }
        });




    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listView) {
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//            menu.setHeaderTitle(Person);
            String[] menuItems = new String[]{"Edit","Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.*/

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent in=new Intent(NewMainActivity.this,CreatePageActivity.class);
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        person=new DbHelper(NewMainActivity.this).getAllPersons();

        ListView lv= (ListView) findViewById(R.id.listView);
        lv.setLongClickable(true);
        if(person.size()==0){
            TextView tv= (TextView) findViewById(R.id.warning);
            tv.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        }else{
            registerForContextMenu(lv);
            lv.setAdapter(new ListAdapter(NewMainActivity.this,person));
        }
    }
}
