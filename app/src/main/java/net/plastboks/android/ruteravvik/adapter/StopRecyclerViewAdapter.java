package net.plastboks.android.ruteravvik.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.viewholder.StopViewHolder;
import net.plastboks.android.ruteravvik.fragment.listener.OnStopInteractionListener;
import net.plastboks.android.ruteravvik.model.Stop;

import java.util.List;

public class StopRecyclerViewAdapter extends RecyclerView.Adapter<StopViewHolder>
{

    private final List<Stop> values;
    private final OnStopInteractionListener listener;

    public StopRecyclerViewAdapter(List<Stop> items, OnStopInteractionListener listener)
    {
        values = items;
        this.listener = listener;
    }

    @Override
    public StopViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_stop, parent, false);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StopViewHolder holder, int position)
    {
        holder.item = values.get(position);
        Stop stop = holder.item;

        holder.stopTitle.setText(stop.getName());
        String subtitle = String.format("%s%s",
                stop.getDistrict(), stop.getZone().length() > 0 || stop.getZone().equals("0")
                        ? String.format(": %s", stop.getZone())
                        : "");
        holder.stopSubtitle.setText(subtitle);

        holder.view.setOnClickListener((view) ->
        {
            if (null != listener) {
                listener.onStopInteraction(holder.item);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return values.size();
    }
}
