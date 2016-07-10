package net.plastboks.android.ruteravvik.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.StopRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnStopInteractionListener;
import net.plastboks.android.ruteravvik.model.Stop;
import net.plastboks.android.ruteravvik.presenter.StopsByLinePresenter;
import net.plastboks.android.ruteravvik.util.ItemDividerDecorator;

import java.util.List;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(StopsByLinePresenter.class)
public class StopsByLineFragment extends BaseFragment<StopsByLinePresenter, List<Stop>>
{
    public static final String TAG = StopsByLineFragment.class.getSimpleName();
    private static final String ARGS_LINE_ID = "lineId";

    private RecyclerView recyclerView;
    private OnStopInteractionListener listener;

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
            getPresenter().request(getArguments().getInt(ARGS_LINE_ID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stop_list, container, false);

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
    public void loadContent(List<Stop> stops)
    {
        recyclerView.setAdapter(new StopRecyclerViewAdapter(stops, listener));
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
