package net.plastboks.s114143.ruteravvik;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.plastboks.s114143.ruteravvik.storage.PersistentCache;

public class AboutActivity extends AppCompatActivity
{
    private ImageView logo;
    private int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        logo = (ImageView)findViewById(R.id.logo);

        logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { onImageClick(); }
        });
    }

    private void onImageClick()
    {
        if (clicks++ > 5) {
            clicks = 0;
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cache))
                    .setMessage(getString(R.string.delete_cache))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            PersistentCache.clearStops();
                            PersistentCache.clearLines();

                            Toast.makeText(AboutActivity.this, getString(R.string.cache_deleted),
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }

    }
}
