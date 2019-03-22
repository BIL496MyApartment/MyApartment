package com.bil495calendear.bitirmeprojesi;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsAdapter extends FragmentPagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 : // istekler tab i
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1 : // sohbetler
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "SOHBETLER";
            case 1:
                return "ARKADASLAR";
            default:
                return null;


        }

    }
}
