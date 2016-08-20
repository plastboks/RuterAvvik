package net.plastboks.android.ruteravvik.adapter.recycler;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.viewholder.LineViewHolder;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.util.TransportationType;

import java.util.List;

public class LineRecyclerViewAdapter extends RecyclerView.Adapter<LineViewHolder>
{

    private final List<Line> lines;
    private final OnLineInteractionListener listener;

    public LineRecyclerViewAdapter(List<Line> items, OnLineInteractionListener listener)
    {
        lines = items;
        this.listener = listener;
    }

    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_line, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LineViewHolder holder, int position)
    {
        holder.item = lines.get(position);
        Line line = holder.item;

        holder.num.setText(String.valueOf(line.getName()));
        holder.lineIcon.setImageDrawable(TransportationType.getType(line.getTransportation()).getDrawable());

        if (line.getLineColour() != null) {
            try {
                GradientDrawable drawable = (GradientDrawable) holder.relativeLayout.getBackground();
                drawable.setColor(Color.parseColor("#aa" + line.getLineColour()));
                //holder.relativeLayout.setBackgroundColor(Color.parseColor("#aa" + line.getLineColour()));
                //holder.relativeLayout.setBackgroundResource(R.drawable.rounded_corners);
            } catch (NumberFormatException nfe) {}
        }

        holder.view.setOnClickListener((view) ->
        {
            if (null != listener) {
                listener.onLineInteraction(holder.item);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return lines.size();
    }
}
