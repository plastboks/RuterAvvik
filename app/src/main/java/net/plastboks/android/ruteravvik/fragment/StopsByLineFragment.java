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
import net.plastboks.android.ruteravvik.adapter.recycler.StopRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnStopInteractionListener;
import net.plastboks.android.ruteravvik.model.Stop;
import net.plastboks.android.ruteravvik.presenter.StopsByLinePresenter;
import net.plastboks.android.ruteravvik.util.ItemDividerDecorator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(StopsByLinePresenter.class)
public class StopsByLineFragment extends BaseFragment<StopsByLinePresenter, List<Stop>>
{
    public static final String TAG = StopsByLineFragment.class.getSimpleName();
    private static final String ARGS_LINE_ID = "lineId";

    private OnStopInteractionListener listener;

    private int lineId = 0;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.list)
    protected RecyclerView recyclerView;

    public StopsByLineFragment()
    {
    }

    public static StopsByLineFragment newInstance(int lineId)
    {
        StopsByLineFragment fragment = new StopsByLineFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_LINE_ID, lineId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            lineId = getArguments().getInt(ARGS_LINE_ID);
        }

        getPresenter().request(lineId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_stop, container, false);

        ButterKnife.bind(this, view);

        Context context = view.getContext();

        swipeRefresh.post(() -> swipeRefresh.setRefreshing(true));
        swipeRefresh.setOnRefreshListener(() -> getPresenter().request(lineId));

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
    public void loadContent(List<Stop> stops)
    {
        recyclerView.setAdapter(new StopRecyclerViewAdapter(stops, listener));
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(false));
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnStopInteractionListener) {
            listener = (OnStopInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStopInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }
}
