package net.plastboks.android.ruteravvik.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.plastboks.android.ruteravvik.fragment.LinesFragment;
import net.plastboks.android.ruteravvik.util.TransportationType;

public class LinesPagerAdapter extends FragmentStatePagerAdapter
{
    private Context context;

    public LinesPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);

        this.context = context;

    }

    @Override
    public Fragment getItem(int position)
    {
        return LinesFragment.newInstance(position);
    }

    @Override
    public int getCount()
    {
        return TransportationType.values().length;
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return TransportationType.getType(position).getValue();
    }
}
