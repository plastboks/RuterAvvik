package net.plastboks.android.ruteravvik.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.StopVisitRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.presenter.StopVisitPresenter;
import net.plastboks.android.ruteravvik.util.ItemDividerDecorator;

import java.util.List;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(StopVisitPresenter.class)
public class StopVisitFragment extends BaseFragment<StopVisitPresenter, List<MonitoredStopVisit>>
{
    public static final String TAG = StopVisitFragment.class.getSimpleName();

    private static final String ARGS_STATION_ID = "stationId";

    private OnLineInteractionListener listener;
    private RecyclerView recyclerView;

    public StopVisitFragment()
    {
    }

    public static StopVisitFragment newInstance(int stationId)
    {
        StopVisitFragment fragment = new StopVisitFragment();
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
            getPresenter().request(getArguments().getInt(ARGS_STATION_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stopvisit_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
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
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnLineInteractionListener) {
            listener = (OnLineInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}
