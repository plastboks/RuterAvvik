package net.plastboks.s114143.ruteravvik.fragment;

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

import net.plastboks.rutersugar.type.Stop;
import net.plastboks.s114143.ruteravvik.MainActivity;
import net.plastboks.s114143.ruteravvik.R;
import net.plastboks.s114143.ruteravvik.adapter.StopAdapter;
import net.plastboks.s114143.ruteravvik.storage.PersistentCache;
import net.plastboks.s114143.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StopsBySearchFragment extends ListFragment
{
    public static final String TAG = "StopsBySearchFragment";
    private static final String ARG_TITLE = "title";

    private String title;

    private List<StopAdapter.ListViewStop> mStops;
    private List<Stop> stops;
    private OnStopBySearchInteraction mListener;
    private AutoCompleteTextView autoCompleteTextView;

    private ProgressBar progressBar;

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

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

   @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_line_autocomp, container, false);

        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.autocomplete);
        autoCompleteTextView.setDropDownHeight(0);

        getActivity().setTitle(title);

        fetchStops();

        return view;
    }

    private void fetchStops()
    {
        if (!PersistentCache.hasStops()) {

            MainActivity.ruter.callback().getStopsRuter(new Callback<List<Stop>>()
            {
                @Override
                public void success(List<Stop> s, Response response)
                {
                    stops = s;
                    PersistentCache.setStops(stops);
                    update();
                }

                @Override
                public void failure(RetrofitError error)
                {
                    MainActivity.pushToast(R.string.unable_to_connect_to_ruter,
                            Toast.LENGTH_SHORT);
                }
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
