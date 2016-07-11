package net.plastboks.android.ruteravvik.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.model.Line;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineViewHolder extends RecyclerView.ViewHolder
{
    public final View view;

    @BindView(R.id.line_num)
    public TextView num;
    @BindView(R.id.color_space)
    public TextView colorSpace;
    @BindView(R.id.line_icon)
    public ImageView lineIcon;

    public Line item;

    public LineViewHolder(View view)
    {
        super(view);
        this.view = view;

        ButterKnife.bind(this, view);
    }
}
