package com.fixture.football.football;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by bhavesh on 16/3/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    String league_id;
    public PagerAdapter(FragmentManager fragmentManager , int mNumOfTabs,String league_id){
        super(fragmentManager);
        this.mNumOfTabs = mNumOfTabs;
        this.league_id = league_id;
    }

    @Override
    public Fragment getItem(int position){
        Bundle args = new Bundle();
        args.putString("league_id", this.league_id);
        switch (position){
            case 0:
                FixtureTab tab1 = new FixtureTab();
                tab1.setArguments(args);
                return tab1;
            case 1:
                ResultTab tab2 = new ResultTab();
                tab2.setArguments(args);
                return tab2;
            case 2:
                StandingsTab tab3 = new StandingsTab();
                tab3.setArguments(args);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;
    }

}
