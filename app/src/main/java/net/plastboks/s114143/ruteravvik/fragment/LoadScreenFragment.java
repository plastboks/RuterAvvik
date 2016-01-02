package net.plastboks.s114143.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.plastboks.rutersugar.type.Line;
import net.plastboks.rutersugar.type.Stop;
import net.plastboks.s114143.ruteravvik.MainActivity;
import net.plastboks.s114143.ruteravvik.R;
import net.plastboks.s114143.ruteravvik.storage.PersistentCache;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadScreenFragment extends Fragment
{
    private OnLoadScreenDoneListener mListener;

    public static LoadScreenFragment newInstance()
    {
        LoadScreenFragment fragment = new LoadScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public LoadScreenFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_load_screen, container, false);

        fetchData();

        return view;
    }

    private void fetchData()
    {
        MainActivity.ruter.callback().getStopsRuter(new Callback<List<Stop>>()
        {
            @Override
            public void success(List<Stop> stops, Response response)
            {
                PersistentCache.setStops(stops);
                MainActivity.ruter.callback().getLines(new Callback<List<Line>>()
                {
                    @Override
                    public void success(List<Line> lines, Response response)
                    {
                        PersistentCache.setLines(lines);
                        onFetchFinish();
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        MainActivity.pushToast(R.string.failed_lines, Toast.LENGTH_SHORT);
                    }
                });
            }
            @Override
            public void failure(RetrofitError error)
            {
                MainActivity.pushToast(R.string.failed_stops, Toast.LENGTH_SHORT);
            }
        });
    }

    public void onFetchFinish()
    {
        mListener.onLoadScreenDone();
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            mListener = (OnLoadScreenDoneListener)activity;
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

    public interface OnLoadScreenDoneListener
    {
        void onLoadScreenDone();
    }

}
