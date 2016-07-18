package net.plastboks.android.ruteravvik.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.fragment.StopsByLineFragment;
import net.plastboks.android.ruteravvik.fragment.listener.OnStopInteractionListener;
import net.plastboks.android.ruteravvik.model.Stop;

public class LineActivity extends AppCompatActivity
    implements OnStopInteractionListener
{
    public static final String TAG = LineActivity.class.getSimpleName();

    public static final String ARGS_LINE_ID = "lineId";
    public static final String ARGS_TITLE = "title";

    private int lineId = 0;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        if (getIntent().getExtras() != null) {
            lineId = getIntent().getExtras().getInt(ARGS_LINE_ID);
            title = getIntent().getExtras().getString(ARGS_TITLE);
        }

        setTitle(title);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container, StopsByLineFragment.newInstance(lineId))
                .commit();
    }

    @Override
    public void onStopInteraction(Stop stop)
    {
        Intent intent = new Intent(this, StationActivity.class);
        intent.putExtra(StationActivity.ARGS_STATION_ID, stop.getRuterId());
        intent.putExtra(StationActivity.ARGS_STATION_TITLE, stop.getName());

        startActivity(intent);
    }
}
