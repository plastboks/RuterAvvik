package net.plastboks.android.ruteravvik.fragment;

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

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.adapter.LineAdapter;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.repository.LinesRepository;
import net.plastboks.android.ruteravvik.storage.PersistentCache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinesBySearchListFragment extends ListFragment
{
    public static final String TAG = LinesBySearchListFragment.class.getSimpleName();

    private static final String ARG_TITLE = "title";

    private String title;

    private List<LineAdapter.ListViewLine> linesAdapter;
    private List<Line> lines = new LinkedList<>();
    private OnLineInteraction listener;
    private View rootView;
    private LineAdapter lineAdapter;

    @BindView(R.id.autocomplete)
    protected AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    @Inject protected LinesRepository linesRepository;

    public static LinesBySearchListFragment newInstance(String title)
    {
        LinesBySearchListFragment fragment = new LinesBySearchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public LinesBySearchListFragment() {}

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

        ButterKnife.bind(this, rootView);

        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setDropDownHeight(0);

        getActivity().setTitle(title);

        fetchLines();

        return rootView;
    }

    private void fetchLines()
    {
        if (!PersistentCache.hasLines()) {
            linesRepository.getLinesRx()
                .doOnCompleted(() -> {})
                .doOnError(throwable -> {
                    Toast.makeText(App.getInstance().getApplicationContext(),
                            R.string.unable_to_connect_to_ruter, Toast.LENGTH_SHORT).show();
                })
                .subscribe(lines -> {
                    this.lines = lines;
                    PersistentCache.setLines(lines);
                    update();
                });
        } else {
            lines = PersistentCache.getLines();
            update();
        }
    }

    private void update()
    {
        linesAdapter = new ArrayList<>();

        for (Line line : lines) {
            linesAdapter.add(new LineAdapter.ListViewLine(line));
        }

        lineAdapter = new LineAdapter(getActivity().getBaseContext(), linesAdapter);

        autoCompleteTextView.setAdapter(lineAdapter);

        setListAdapter(lineAdapter);

        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            listener = (OnLineInteraction) activity;
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

        if (null != listener) {
            LineAdapter.ListViewLine viewLine =
                    (LineAdapter.ListViewLine)l.getAdapter().getItem(position);
            String title = String.format("%s %s", viewLine.num, viewLine.title);
            listener.onLineInteraction(title, viewLine.id);
        }
    }

    public interface OnLineInteraction
    {
        void onLineInteraction(String title, int id);
    }
}
