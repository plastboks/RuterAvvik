package net.plastboks.android.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.repository.StopsRepository;
import net.plastboks.java.rutersugar.model.Stop;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.StopAdapter;
import net.plastboks.android.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopsByLineIdFragment extends ListFragment
        implements SwipeRefreshLayout.OnRefreshListener
{
    public static final String TAG = "StopsByLineIdFragment";
    private static final String ARG_ID = "lineID";
    private static final String ARG_TITLE = "title";

    private String title;
    private int id;

    private List<StopAdapter.ListViewStop> mStops;
    private OnStopByLineInteraction mListener;
    private List<Stop> stops;
    private View rootView;

    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Inject protected StopsRepository stopsRepository;

    public static StopsByLineIdFragment newInstance(String title, int id)
    {
        StopsByLineIdFragment fragment = new StopsByLineIdFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StopsByLineIdFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        App.getInstance().getDiComponent().inject(this);

        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
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
        showSpinner();

        fetchStops();

        return rootView;
    }

    private void fetchStops()
    {
        stopsRepository.getStopsByLineIdRx(id)
                .doOnCompleted(() -> {})
                .doOnError(throwable -> {
                    MainActivity.pushToast(R.string.unable_to_connect_to_ruter,
                            Toast.LENGTH_SHORT);
                })
                .subscribe(stops -> {
                    this.stops = stops;
                    update();
                });
    }

    private void showSpinner()
    {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    private void update()
    {
        mStops = new ArrayList<>();

        for (Stop stop : stops) {
            mStops.add(new StopAdapter.ListViewStop(stop,
                    Settings.containsFavorite(stop)));
        }

        StopAdapter stopAdapter = new StopAdapter(getActivity().getBaseContext(), mStops);

        setListAdapter(stopAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
                showSpinner();
                onRefresh();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnStopByLineInteraction) activity;
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
            mListener.onStopByLineInteraction(stops.get(position).getName(),
                    stops.get(position).getId());
    }

    @Override
    public void onRefresh() { fetchStops(); }

    public interface OnStopByLineInteraction
    {
        public void onStopByLineInteraction(String title, int id);
    }

}
