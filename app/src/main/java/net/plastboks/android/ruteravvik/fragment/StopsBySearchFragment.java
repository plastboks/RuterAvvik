package net.plastboks.android.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.repository.StopsRepository;
import net.plastboks.java.rutersugar.model.Stop;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.StopAdapter;
import net.plastboks.android.ruteravvik.storage.PersistentCache;
import net.plastboks.android.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopsBySearchFragment extends ListFragment
{
    public static final String TAG = "StopsBySearchFragment";
    private static final String ARG_TITLE = "title";

    private String title;

    private List<StopAdapter.ListViewStop> mStops;
    private List<Stop> stops;
    private OnStopBySearchInteraction mListener;

    @BindView(R.id.autocomplete)
    protected AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    @Inject protected StopsRepository stopsRepository;

    public static StopsBySearchFragment newInstance(String title)
    {
        StopsBySearchFragment fragment = new StopsBySearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StopsBySearchFragment() {}

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_line_autocomp, container, false);

        ButterKnife.bind(this, view);

        autoCompleteTextView.setDropDownHeight(0);

        getActivity().setTitle(title);

        fetchStops();

        return view;
    }

    private void fetchStops()
    {
        if (!PersistentCache.hasStops()) {

            stopsRepository.getStopsRuterRx()
                    .doOnCompleted(() -> {})
                    .doOnError(throwable -> {
                        MainActivity.pushToast(R.string.unable_to_connect_to_ruter,
                                Toast.LENGTH_SHORT);
                    })
                    .subscribe(stops -> {
                        this.stops = stops;
                        PersistentCache.setStops(stops);
                        update();
                    });
        } else {
            stops = PersistentCache.getStops();
            update();
        }
    }

    private void update()
    {
        mStops = new ArrayList<>();

        for (Stop stop : stops) {
            mStops.add(new StopAdapter.ListViewStop(stop,
                    Settings.containsFavorite(stop)));
        }

        StopAdapter stopAdapter = new StopAdapter(getActivity().getBaseContext(), mStops);

        autoCompleteTextView.setAdapter(stopAdapter);

        setListAdapter(stopAdapter);

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnStopBySearchInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStopByGpsInteraction");
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

        if (null != mListener) {
            StopAdapter.ListViewStop viewStop =
                    (StopAdapter.ListViewStop)l.getAdapter().getItem(position);
            mListener.onStopBySearchInteraction(viewStop.title, viewStop.id);
        }
    }

    public interface OnStopBySearchInteraction
    {
        public void onStopBySearchInteraction(String title, int id);
    }

}
