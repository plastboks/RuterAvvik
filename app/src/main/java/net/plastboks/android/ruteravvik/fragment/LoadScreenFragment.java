package net.plastboks.android.ruteravvik.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.java.rutersugar.domain.Line;
import net.plastboks.java.rutersugar.domain.Stop;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.storage.PersistentCache;
import net.plastboks.java.rutersugar.service.LineService;
import net.plastboks.java.rutersugar.service.StopService;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoadScreenFragment extends Fragment
{
    private OnLoadScreenDoneListener mListener;

    @Inject public StopService stopService;
    @Inject public LineService lineService;

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

        App.getInstance().getDiComponent().inject(this);
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
        stopService.getStopsRuter().enqueue(new Callback<List<Stop>>()
        {
            @Override
            public void onResponse(Response<List<Stop>> response, Retrofit retrofit)
            {
                PersistentCache.setStops(response.body());
                lineService.getLines().enqueue(new Callback<List<Line>>()
                {
                    @Override
                    public void onResponse(Response<List<Line>> response, Retrofit retrofit)
                    {
                        PersistentCache.setLines(response.body());
                        onFetchFinish();
                    }

                    @Override
                    public void onFailure(Throwable t)
                    {
                        MainActivity.pushToast(R.string.failed_lines, Toast.LENGTH_SHORT);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t)
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
