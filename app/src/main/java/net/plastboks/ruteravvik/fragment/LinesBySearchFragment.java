package net.plastboks.ruteravvik.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.plastboks.ruteravvik.App;
import net.plastboks.rutersugar.domain.Line;
import net.plastboks.ruteravvik.activity.MainActivity;
import net.plastboks.ruteravvik.R;
import net.plastboks.ruteravvik.adapter.LineAdapter;
import net.plastboks.ruteravvik.storage.PersistentCache;
import net.plastboks.rutersugar.service.LineService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LinesBySearchFragment extends ListFragment
{
    public static final String TAG = "LinesBySearchFragment";
    private static final String ARG_TITLE = "title";

    private String title;

    private List<LineAdapter.ListViewLine> mLines;
    private List<Line> lines = new LinkedList<>();
    private OnLineInteraction mListener;
    private AutoCompleteTextView autoCompleteTextView;
    private View rootView;
    private LineAdapter lineAdapter;

    private ProgressBar progressBar;

    @Inject public LineService lineService;

    public static LinesBySearchFragment newInstance(String title)
    {
        LinesBySearchFragment fragment = new LinesBySearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public LinesBySearchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        App.getInstance().getDiComponent().inject(this);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_line_autocomp, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);

        autoCompleteTextView = (AutoCompleteTextView)rootView.findViewById(R.id.autocomplete);
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setDropDownHeight(0);

        getActivity().setTitle(title);

        fetchLines();

        return rootView;
    }

    private void fetchLines()
    {
        if (!PersistentCache.hasLines()) {
            lineService.getLines().enqueue(new Callback<List<Line>>()
            {
                @Override
                public void onResponse(Response<List<Line>> response, Retrofit retrofit)
                {
                    lines = response.body();
                    PersistentCache.setLines(lines);
                    update();
                }

                @Override
                public void onFailure(Throwable t)
                {
                    MainActivity.pushToast(R.string.unable_to_connect_to_ruter,
                            Toast.LENGTH_SHORT);
                }
            });
        } else {
            lines = PersistentCache.getLines();
            update();
        }
    }

    private void update()
    {
        mLines = new ArrayList<>();

        for (Line line : lines) {
            mLines.add(new LineAdapter.ListViewLine(line));
        }

        lineAdapter = new LineAdapter(getActivity().getBaseContext(), mLines);

        autoCompleteTextView.setAdapter(lineAdapter);

        setListAdapter(lineAdapter);

        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnLineInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnLineInteraction");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            LineAdapter.ListViewLine viewLine =
                    (LineAdapter.ListViewLine)l.getAdapter().getItem(position);
            String title = String.format("%s %s", viewLine.num, viewLine.title);
            mListener.onLineInteraction(title, viewLine.id);
        }
    }

    public interface OnLineInteraction
    {
        void onLineInteraction(String title, int id);
    }
}
