package net.plastboks.android.ruteravvik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.presenter.SplashPresenter;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(SplashPresenter.class)
public class SplashActivity extends BaseActivity<SplashPresenter, Object>
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getPresenter().request();
    }

    @Override
    public void loadContent(Object o)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onItemsError(Throwable throwable)
    {
        super.onItemsError(throwable);
        Toast.makeText(this, getString(R.string.failed_lines), Toast.LENGTH_SHORT).show();
    }
}
