package net.plastboks.android.ruteravvik.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.recycler.StopVisitRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.presenter.StopVisitPresenter;
import net.plastboks.android.ruteravvik.util.ItemDividerDecorator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(StopVisitPresenter.class)
public class StopsByStationFragment extends BaseFragment<StopVisitPresenter, List<MonitoredStopVisit>>
{
    public static final String TAG = StopsByStationFragment.class.getSimpleName();

    private static final String ARGS_STATION_ID = "stationId";

    private OnLineInteractionListener listener;

    private int stationId = 0;


    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.list)
    protected RecyclerView recyclerView;

    public StopsByStationFragment()
    {
    }

    public static StopsByStationFragment newInstance(int stationId)
    {
        StopsByStationFragment fragment = new StopsByStationFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_STATION_ID, stationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            stationId = getArguments().getInt(ARGS_STATION_ID);
        }

        getPresenter().request(stationId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_stopvisit, container, false);

        ButterKnife.bind(this, view);

        Context context = view.getContext();

        swipeRefresh.post(() -> swipeRefresh.setRefreshing(true));
        swipeRefresh.setOnRefreshListener(() -> getPresenter().request(stationId));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDividerDecorator(getContext()));

        return view;
    }

    @Override
    public void onItemsError(Throwable throwable)
    {
        super.onItemsError(throwable);
    }

    @Override
    public void loadContent(List<MonitoredStopVisit> monitoredStopVisitList)
    {
        recyclerView.setAdapter(new StopVisitRecyclerViewAdapter(monitoredStopVisitList,
                listener));
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(false));
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnLineInteractionListener) {
            listener = (OnLineInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLineInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}
