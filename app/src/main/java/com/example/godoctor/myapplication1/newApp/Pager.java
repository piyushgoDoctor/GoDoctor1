package com.example.godoctor.myapplication1.newApp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by godoctor on 23/1/17.
 */
public class Pager extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FamilyFragment tab1 = new FamilyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("message", "Family" );
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                FamilyFragment tab2 = new FamilyFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("message", "Friends" );
                tab2.setArguments(bundle1);
                return tab2;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
