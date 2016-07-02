package net.plastboks.android.ruteravvik.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.storage.PersistentCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity
{
    @BindView(R.id.logo)
    protected ImageView logo;

    private int clicks = 0;

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
        if (clicks++ > 5) {
            clicks = 0;
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cache))
                    .setMessage(getString(R.string.delete_cache))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        PersistentCache.clearStops();
                        PersistentCache.clearLines();

                        Toast.makeText(AboutActivity.this, getString(R.string.cache_deleted),
                                Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }

    }
}
