package net.plastboks.android.ruteravvik.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.recycler.LineRecyclerViewAdapter;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.presenter.LinesBySearchPresenter;

import java.util.List;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LinesBySearchPresenter.class)
public class LinesBySearchFragment extends BaseFragment<LinesBySearchPresenter, List<Line>>
{

    private OnListFragmentInteractionListener listener;
    private RecyclerView recyclerView;

    public LinesBySearchFragment() {}

    public static LinesBySearchFragment newInstance()
    {
        LinesBySearchFragment fragment = new LinesBySearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getPresenter().request();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_line_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void loadContent(List<Line> lines)
    {
        recyclerView.setAdapter(new LineRecyclerViewAdapter(lines, listener));
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
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
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

    public interface OnListFragmentInteractionListener
    {
        void onListFragmentInteraction(Line item);
    }
}
