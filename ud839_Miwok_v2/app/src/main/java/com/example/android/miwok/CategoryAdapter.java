package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private final int numTabs = 4;
    private String tabTitles[] = new String[] { "Numbers", "Family", "Colors", "Phrases" };

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragmentToDisplay;

        switch (position){
            case 0:
                fragmentToDisplay = new NumbersFragment();
                break;
            case 1:
                fragmentToDisplay = new FamilyFragment();
                break;
            case 2:
                fragmentToDisplay =  new ColorsFragment();
                break;
            case 3:
                fragmentToDisplay =  new PhrasesFragment();
                break;
            default:
                fragmentToDisplay = null;
        }
        return fragmentToDisplay;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
