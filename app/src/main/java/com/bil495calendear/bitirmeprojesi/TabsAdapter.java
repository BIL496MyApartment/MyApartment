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
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;
            case 1 : // sohbetler
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 2 : // arkadaslar
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;
            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "ISTEKLER";
            case 1:
                return "SOHBETLER";
            case 2:
                return "ARKADASLAR";
            default:
                return null;


        }

    }
}
