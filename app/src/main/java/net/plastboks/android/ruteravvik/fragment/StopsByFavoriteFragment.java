package net.plastboks.android.ruteravvik.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.plastboks.java.rutersugar.domain.Stop;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.StopAdapter;
import net.plastboks.android.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopsByFavoriteFragment extends ListFragment
        implements SwipeRefreshLayout.OnRefreshListener
{
    public static final String TAG = "StopsByLineIdFragment";
    private static final String ARG_TITLE = "title";

    private String title;

    private List<StopAdapter.ListViewStop> mStops;
    private OnStopByFavoriteInteraction mListener;
    private View rootView;
    private List<Stop> favorites;

    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(android.R.id.empty)
    protected TextView empty;

    public static StopsByFavoriteFragment newInstance(String title)
    {
        StopsByFavoriteFragment fragment = new StopsByFavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StopsByFavoriteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_line_swipe, container, false);

        ButterKnife.bind(this, rootView);

        swipeRefreshLayout.setOnRefreshListener(this);

        getActivity().setTitle(title);

        showLoader(true);

        update();

        return rootView;
    }

    private void showLoader(final boolean bool)
    {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(bool));
    }

    private void update()
    {
        mStops = new ArrayList<>();

        favorites = Settings.getFavorites();

        if (favorites.isEmpty()) {
            empty.setText(getActivity().getString(R.string.empty_list));

        } else {
            for (Stop stop : favorites) {
                mStops.add(new StopAdapter.ListViewStop(stop, true));
            }
            setListAdapter(new StopAdapter(getActivity().getBaseContext(), mStops));
        }

        showLoader(false);

    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnStopByFavoriteInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStopVisitInteraction");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        if (null != mListener)
            mListener.onStopByFavoriteInteraction(favorites.get(position).getName(),
                    favorites.get(position).getId());
    }

    @Override
    public void onRefresh()
    {
        update();
    }

    public interface OnStopByFavoriteInteraction
    {
        public void onStopByFavoriteInteraction(String title, int id);
    }

}
