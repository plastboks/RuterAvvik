package net.plastboks.android.ruteravvik.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.recycler.LineRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.presenter.LinesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LinesPresenter.class)
public class LinesFragment extends BaseSupportFragment<LinesPresenter, List<Line>>
{
    public static final String TAG = LinesFragment.class.getSimpleName();

    private static final String ARGS_TRANSPORTATION_TYPE = "transportationType";
    private static final String ARGS_COLUMN_COUNT = "columnCount";

    private int transportationType;
    private int columnCount;

    private OnLineInteractionListener listener;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.list)
    protected RecyclerView recyclerView;

    @BindView(android.R.id.empty)
    protected TextView empty;

    public LinesFragment() {}

    public static LinesFragment newInstance(int transportationType, int columnCount)
    {
        LinesFragment fragment = new LinesFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_TRANSPORTATION_TYPE, transportationType);
        args.putInt(ARGS_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            transportationType = getArguments().getInt(ARGS_TRANSPORTATION_TYPE);
            columnCount = getArguments().getInt(ARGS_COLUMN_COUNT);
        }

        getPresenter().request(transportationType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_line, container, false);

        ButterKnife.bind(this, view);

        Context context = view.getContext();

        swipeRefresh.post(() -> swipeRefresh.setRefreshing(true));
        swipeRefresh.setOnRefreshListener(() -> getPresenter().request(transportationType));

        //recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        //recyclerView.addItemDecoration(new ItemDividerDecorator(getContext()));

        return view;
    }

    @Override
    public void loadContent(List<Line> lines)
    {
        recyclerView.setAdapter(new LineRecyclerViewAdapter(lines, listener));
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(false));
    }

    @Override
    public void onItemsError(Throwable throwable)
    {
        super.onItemsError(throwable);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnLineInteractionListener) {
            listener = (OnLineInteractionListener) context;
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
