package net.plastboks.android.ruteravvik.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopVisitViewHolder extends RecyclerView.ViewHolder
{
    public final View view;
    @BindView(R.id.line_num)
    public TextView num;
    @BindView(R.id.line_title)
    public TextView title;
    @BindView(R.id.item_platform)
    public TextView platform;
    @BindView(R.id.item_departure)
    public TextView departure;
    @BindView(R.id.item_deviation)
    public TextView deviation;
    @BindView(R.id.color_space)
    public TextView colorSpace;
    @BindView(R.id.mask_icon)
    public ImageView maskIcon;

    public MonitoredStopVisit item;

    public StopVisitViewHolder(View view)
    {
        super(view);
        this.view = view;

        ButterKnife.bind(this, view);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + title.getText() + "'";
    }
}
