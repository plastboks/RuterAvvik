package net.plastboks.android.ruteravvik.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.viewholder.StopVisitViewHolder;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.model.MonitoredVehicleJourney;
import net.plastboks.android.ruteravvik.storage.PersistentCache;
import net.plastboks.android.ruteravvik.util.Datehelper;

import java.util.HashMap;
import java.util.List;

public class StopVisitRecyclerViewAdapter extends RecyclerView.Adapter<StopVisitViewHolder>
{

    private final List<MonitoredStopVisit> values;
    private final OnLineInteractionListener listener;

    public StopVisitRecyclerViewAdapter(List<MonitoredStopVisit> items,
                                        OnLineInteractionListener listener)
    {
        values = items;
        this.listener = listener;
    }

    @Override
    public StopVisitViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_stopvisit, parent, false);
        return new StopVisitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StopVisitViewHolder holder, int position)
    {
        holder.item = values.get(position);
        MonitoredStopVisit item = holder.item;

        HashMap<String, String> colors = PersistentCache.getColors();
        MonitoredVehicleJourney transport = item.getMonitoredVehicleJourney();

        holder.num.setText(transport.getPublishedLineName());
        holder.title.setText(transport.getDestinationName());
        holder.platform.setText(String.format("%s",
                transport.getMonitoredCall().getDeparturePlatformName()));
        holder.departure.setText(Datehelper.getTimeSecond(
                transport.getMonitoredCall().getExpectedDepartureTime()));
        holder.deviation.setText(Datehelper.getTimeDiff(
                transport.getMonitoredCall().getAimedDepartureTime(),
                transport.getMonitoredCall().getExpectedDepartureTime()
        ));

        String bgColor = colors.get(transport.getPublishedLineName());

        if (bgColor != null) {
            try {
                holder.colorSpace.setBackgroundColor(Color.parseColor("#" + bgColor));
            } catch (NumberFormatException nfe) {}
        }

        holder.view.setOnClickListener((view) ->
        {
            if (null != listener) {
                //listener.onLineInteraction(holder.item);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return values.size();
    }
}
