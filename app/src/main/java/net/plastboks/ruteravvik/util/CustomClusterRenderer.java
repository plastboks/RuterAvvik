package net.plastboks.ruteravvik.util;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import net.plastboks.ruteravvik.fragment.StopsByMapFragment;

public class CustomClusterRenderer extends DefaultClusterRenderer<StopsByMapFragment.RuterItem>
{

    public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager clusterManager)
    {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(StopsByMapFragment.RuterItem item,
                                               MarkerOptions markerOptions)
    {
        super.onBeforeClusterItemRendered(item, markerOptions);
        markerOptions.title(item.getTitle());
        markerOptions.snippet(item.getSnippet());

    }
}
