package net.plastboks.android.ruteravvik.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.plastboks.android.ruteravvik.fragment.LinesFragment;
import net.plastboks.android.ruteravvik.util.TransportationType;

import java.util.Arrays;
import java.util.List;

public class LinesPagerAdapter extends FragmentStatePagerAdapter
{
    private Context context;

    private List<TransportationType> tabs = Arrays.asList(
            TransportationType.BUS,
            TransportationType.TRAM,
            TransportationType.TRAIN,
            TransportationType.METRO,
            TransportationType.BOAT,
            TransportationType.AIR_PORT_BUS,
            TransportationType.AIR_PORT_TRAIN
    );

    public LinesPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);

        this.context = context;

    }

    @Override
    public Fragment getItem(int position)
    {
        int columnCount = 3;
        if (position == 0) columnCount = 5;
        return LinesFragment.newInstance(tabs.get(position).getKey(), columnCount);
    }

    @Override
    public int getCount()
    {
        return tabs.size();
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabs.get(position).getValue();
    }
}
