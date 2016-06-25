package net.plastboks.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.plastboks.ruteravvik.App;
import net.plastboks.rutersugar.domain.MonitoredStopVisit;
import net.plastboks.rutersugar.domain.MonitoredVehicleJourney;
import net.plastboks.ruteravvik.activity.MainActivity;
import net.plastboks.ruteravvik.util.Mask;
import net.plastboks.ruteravvik.R;
import net.plastboks.ruteravvik.adapter.StopVisitAdapter;
import net.plastboks.ruteravvik.util.Datehelper;
import net.plastboks.ruteravvik.storage.Settings;
import net.plastboks.rutersugar.service.MonitoredStopVisitService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class StopVisitFragment extends ListFragment
        implements SwipeRefreshLayout.OnRefreshListener
{
    public static final String TAG = "StopVisitFragment";
    private static final String ARG_ID = "stopID";
    private static final String ARG_TITLE = "title";

    private int stationID;
    private String title;

    private List<StopVisitAdapter.ListViewStopVisit> mStopsVisits;
    private OnStopVisitInteraction mListener;
    private List<MonitoredStopVisit> stopVisits;
    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView detailLeft, detailMiddle, detailRight, empty;
    private RelativeLayout detailContainer;

    @Inject public MonitoredStopVisitService monitoredStopVisitService;

    public static StopVisitFragment newInstance(String title, int id)
    {
        StopVisitFragment fragment = new StopVisitFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StopVisitFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        App.getInstance().getDiComponent().inject(this);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            stationID = getArguments().getInt(ARG_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_line_detailswipe, container, false);

        empty = (TextView)rootView.findViewById(android.R.id.empty);

        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        detailContainer = (RelativeLayout)rootView.findViewById(R.id.detail_container);
        detailLeft = (TextView)rootView.findViewById(R.id.detail_left);
        detailMiddle = (TextView)rootView.findViewById(R.id.detail_middle);
        detailRight = (TextView)rootView.findViewById(R.id.detail_right);

        if (!Settings.getBool("show_stats")) detailContainer.setVisibility(View.GONE);

        getActivity().setTitle(title);
        showSpinner(true);

        fetchDepartures();

        return rootView;
    }

    private void fetchDepartures()
    {
        monitoredStopVisitService
                .getDepartures(stationID,
                        Datehelper.getDateTime(Settings.getInt("departure_offset")))
                .enqueue(new Callback<List<MonitoredStopVisit>>()
        {
            @Override
            public void onResponse(Response<List<MonitoredStopVisit>> response, Retrofit retrofit)
            {
                stopVisits = response.body();
                update();
            }

            @Override
            public void onFailure(Throwable t)
            {
                MainActivity.pushToast(R.string.failed_lines, Toast.LENGTH_SHORT);
            }
        });
    }

    private void showSpinner(final boolean bool)
    {
        swipeRefreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(bool);
            }
        });
    }

    private void update()
    {
        showSpinner(false);

        if (stopVisits == null || stopVisits.isEmpty()) {
            empty.setText(getActivity().getString(R.string.empty_list));
            return;
        }

        mStopsVisits = new ArrayList<>();

        List<String> masks = Settings.getMasks();
        boolean filterMode = Settings.getMaskedMode();

        List<Integer> delays = new ArrayList<>();

        List<MonitoredStopVisit> filteredList = filterList(stopVisits);

        for (MonitoredStopVisit stop : filteredList) {
            MonitoredVehicleJourney transport = stop.getMonitoredVehicleJourney();
            String maskedRef = String.format("%d:%s_%s", stationID,
                    transport.getPublishedLineName(), transport.getDestinationName());

            int secDiff = Datehelper.getSecondsDiff(
                        stop.getMonitoredVehicleJourney()
                                .getMonitoredCall().getAimedDepartureTime(),
                        stop.getMonitoredVehicleJourney()
                                .getMonitoredCall().getExpectedDepartureTime());

            if (secDiff < -1000) break; // weird ruter api trash data bug...

            if (filterMode) {
                mStopsVisits.add(new StopVisitAdapter.ListViewStopVisit(stop,
                        new Mask(maskedRef, masks.contains(maskedRef)), stationID));
                delays.add(secDiff);
            } else if (!masks.contains(maskedRef)) {
                    mStopsVisits.add(new StopVisitAdapter
                            .ListViewStopVisit(stop, null, stationID));
                    delays.add(secDiff);
            }
        }

        if (delays.size() > 0) {

            int delaySum = 0;
            for (Integer i : delays) delaySum += i;

            int avgSum = delaySum/delays.size();

            int deviationSquares = 0;
            for (Integer i : delays) deviationSquares += Math.pow(i - avgSum, 2);

            int variance = deviationSquares/delays.size();

            detailLeft.setText(String.format("%s %d/s", "Tot: ", delaySum));
            detailMiddle.setText(String.format("%s %.1f/s", "Avg: ", avgSum + 0.0));
            detailRight.setText(String.format("σ: %.1f", Math.sqrt(variance)));
        }

        StopVisitAdapter stopVisitAdapter = new StopVisitAdapter(
                getActivity().getBaseContext(), mStopsVisits);

        stopVisitAdapter.setMaskedMode(filterMode);
        setListAdapter(stopVisitAdapter);

    }

    private List<MonitoredStopVisit> filterList(List<MonitoredStopVisit> list)
    {
        List<MonitoredStopVisit> newList = new ArrayList<>(list);

        int limit = Settings.getInt("future_departure_limit");

        if (limit == -1)
            limit = MainActivity.getCurContext()
                    .getResources().getInteger(R.integer.default_departure_limit);

        limit += Settings.getInt("departure_offset");

        for (MonitoredStopVisit msv : list) {
            Date d = msv.getMonitoredVehicleJourney()
                    .getMonitoredCall().getExpectedDepartureTime();
            if (Datehelper.after(d, limit)) { newList.remove(msv); }
        }

        return newList;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnStopVisitInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStopVisitInteraction");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh_filter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        showSpinner(true);

        switch (id) {
            case R.id.action_filter_toggle:
                Toast.makeText(getActivity().getBaseContext(),
                        getActivity().getResources().getString(R.string.filter_mode_toggled),
                        Toast.LENGTH_SHORT).show();
                Settings.setMaskedMode(!Settings.getMaskedMode());
                break;
            case R.id.action_refresh:
                break;
        }
        onRefresh();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            StopVisitAdapter.ListViewStopVisit viewLine =
                    (StopVisitAdapter.ListViewStopVisit)l.getAdapter().getItem(position);
            String title = String.format("%s %s", viewLine.num, viewLine.title);
            mListener.onStopVisitInteraction(title, viewLine.id);
        }
    }

    @Override
    public void onRefresh() { fetchDepartures(); }

    public interface OnStopVisitInteraction
    {
        void onStopVisitInteraction(String title, int id);
    }

}