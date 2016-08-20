package net.plastboks.android.ruteravvik.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.fragment.StopsByStationFragment;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.Line;

public class StationActivity extends AppCompatActivity
    implements OnLineInteractionListener
{
    public static final String TAG = StationActivity.class.getSimpleName();

    public static final String ARGS_STATION_ID = "stationId";
    public static final String ARGS_STATION_TITLE = "stationTitle";

    private int stationId = 0;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_visit);

        if (getIntent().getExtras() != null) {
            stationId = getIntent().getExtras().getInt(ARGS_STATION_ID);
            title = getIntent().getExtras().getString(ARGS_STATION_TITLE);
        }

        setTitle(title);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container, StopsByStationFragment.newInstance(stationId))
                .commit();
    }

    @Override
    public void onLineInteraction(Line item)
    {

    }
}
