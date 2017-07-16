package com.example.android.vogue;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public pagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new studsFragment();
        } else if (position == 1) {
            return new threadersFragment();
        } else if (position == 2) {
            return new chandeliersFragment();
        } else if (position == 3) {
            return new earcuffsFragment();
        } else if (position == 4) {
            return new hoopsFragment();
        } else {
            return new jhumkasFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.studs);
        } else if (position == 1) {
            return mContext.getString(R.string.threaders);
        } else if (position == 2) {
            return mContext.getString(R.string.chandeliers);
        } else if (position == 3) {
            return mContext.getString(R.string.earcuffs);
        } else if (position == 4) {
            return mContext.getString(R.string.hoops);
        } else
            return mContext.getString(R.string.jhumkas);
    }
}

