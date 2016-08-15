package net.plastboks.android.ruteravvik.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.model.Stop;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopViewHolder extends RecyclerView.ViewHolder
{
    public final View view;

    @BindView(R.id.stop_title)
    public TextView stopTitle;
    @BindView(R.id.stop_subtitle)
    public TextView stopSubtitle;
    @BindView(R.id.favorite_icon)
    public ImageView favoriteIcon;

    public Stop item;

    public StopViewHolder(View view)
    {
        super(view);
        this.view = view;

        ButterKnife.bind(this, view);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + stopTitle.getText() + "'";
    }
}
