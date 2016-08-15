package net.plastboks.android.ruteravvik.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import net.plastboks.android.ruteravvik.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity
{
    @BindView(R.id.logo)
    protected ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.logo)
    protected void onImageClick()
    {
    }
}
