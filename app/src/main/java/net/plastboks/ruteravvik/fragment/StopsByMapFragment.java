package net.plastboks.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import net.plastboks.rutersugar.domain.Stop;
import net.plastboks.ruteravvik.activity.MainActivity;
import net.plastboks.ruteravvik.R;
import net.plastboks.ruteravvik.util.CustomClusterRenderer;
import net.plastboks.ruteravvik.util.Coordinates;
import net.plastboks.ruteravvik.storage.PersistentCache;

import java.util.HashMap;
import java.util.List;

public class StopsByMapFragment extends Fragment implements OnMapReadyCallback
{
    public static final String TAG = "StopsByMapFragment";
    private static final String ARG_TITLE = "title";

    private String title;
    private OnStopByMapInteraction mListener;

    private static View view;

    List<Stop> stops = PersistentCache.getStops();
    HashMap<String, Integer> refTable = new HashMap<>();

    public class RuterItem implements ClusterItem
    {
        private final LatLng mPosition;
        private String title, snippet;

        public RuterItem(double lat, double lng) {
            mPosition = new LatLng(lat, lng);
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        public void setTitle(String title) { this.title = title; }
        public void setSnippet(String snippet) { this.snippet = snippet; }
        public String getTitle() { return title; }
        public String getSnippet() { return snippet; }
    }

    public static StopsByMapFragment newInstance(String title)
    {
        StopsByMapFragment fragment = new StopsByMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public StopsByMapFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();

            if (parent != null) parent.removeView(view);
        }

        try {
            view = inflater.inflate(R.layout.fragment_stops_by_map,
                    container, false);
        } catch (InflateException e) {}

        MapFragment mapFragment = (MapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getActivity().setTitle(title);

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnStopByMapInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        ClusterManager<RuterItem> mClusterManager;
        mClusterManager = new ClusterManager<>(
                MainActivity.getCurContext(), googleMap);

        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setRenderer(new CustomClusterRenderer(getActivity(),
                googleMap, mClusterManager));

        for (Stop stop : stops) {
            LatLng latLng = Coordinates.ruterConvertUTMToLatLong(stop);
            RuterItem item = new RuterItem(latLng.latitude, latLng.longitude);
            item.setTitle(stop.getName());
            item.setSnippet("Click on me to go to station");
            mClusterManager.addItem(item);

            refTable.put(stop.getName(), stop.getId());

        }

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker marker)
            {
                if (null != mListener) {
                    mListener.onStopByMapInteraction(marker.getTitle(),
                            refTable.get(marker.getTitle()));
                }
            }
        });

    }

    public interface OnStopByMapInteraction
    {
        void onStopByMapInteraction(String title, int id);
    }

}
