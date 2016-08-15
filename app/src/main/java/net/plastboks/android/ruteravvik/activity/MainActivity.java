package net.plastboks.android.ruteravvik.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.adapter.pager.LinesPagerAdapter;
import net.plastboks.android.ruteravvik.fragment.listener.OnLineInteractionListener;
import net.plastboks.android.ruteravvik.fragment.listener.OnStopInteractionListener;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.model.Stop;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
            NavigationView.OnNavigationItemSelectedListener,
            OnLineInteractionListener,
            OnStopInteractionListener
{

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;
    @BindView(R.id.nav_view)
    protected NavigationView navigationView;
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        loadPager();
    }

    private void loadPager()
    {
        viewPager.setAdapter(new LinesPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_lines:
                setTitle(getString(R.string.search_line));
                break;
            case R.id.nav_locations:
                setTitle(getString(R.string.search_loc));
                break;
            case R.id.nav_favourites:
                setTitle(getString(R.string.favorites));
                break;
            case R.id.nav_manage:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLineInteraction(Line item)
    {
        Intent intent = new Intent(this, LineActivity.class);
        intent.putExtra(LineActivity.ARGS_LINE_ID, item.getRuterId());
        intent.putExtra(LineActivity.ARGS_TITLE, item.getName());
        startActivity(intent);
    }

    @Override
    public void onStopInteraction(Stop stop)
    {
        /*
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopsByLineFragment.TAG)
                .replace(R.id.fragment_container, StopsByStationFragment.newInstance(stop.getRuterId()))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        setTitle(stop.getName());
        */
    }
}
