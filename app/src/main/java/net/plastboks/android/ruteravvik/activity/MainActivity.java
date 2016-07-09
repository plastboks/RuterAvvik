package net.plastboks.android.ruteravvik.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.fragment.LinesBySearchListFragment;
import net.plastboks.android.ruteravvik.fragment.StopVisitFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByFavoriteFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByLineIdFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByMapFragment;
import net.plastboks.android.ruteravvik.fragment.StopsBySearchFragment;
import net.plastboks.android.ruteravvik.storage.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
            NavigationView.OnNavigationItemSelectedListener,
            LinesBySearchListFragment.OnLineInteraction,
            StopsByLineIdFragment.OnStopByLineInteraction,
            StopsBySearchFragment.OnStopBySearchInteraction,
            StopsByMapFragment.OnStopByMapInteraction,
            StopVisitFragment.OnStopVisitInteraction,
            StopsByFavoriteFragment.OnStopByFavoriteInteraction
{

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;
    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(true);
    }

    private void loadFragment(boolean emptyStack)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment;

        switch (Settings.getString("default_page")) {
            case "1":
                fragment = LinesBySearchListFragment.newInstance(getString(R.string.search_line));
                break;
            case "2":
                fragment = StopsBySearchFragment.newInstance(getString(R.string.search_loc));
                break;
            case "3":
                fragment = StopsByFavoriteFragment.newInstance(getString(R.string.favorites));
                break;
            default:
                fragment = LinesBySearchListFragment.newInstance(getString(R.string.search_line));
        }

        if (emptyStack) ft.add(R.id.fragment_container, fragment);
        else ft.replace(R.id.fragment_container, fragment);

        ft.commit();
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

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (id) {
            case R.id.nav_search_line:
                ft.replace(R.id.fragment_container,
                        LinesBySearchListFragment.newInstance(getString(R.string.search_line)))
                .addToBackStack(LinesBySearchListFragment.TAG)
                .commit();
                break;
            case R.id.nav_search_loc:
                ft.replace(R.id.fragment_container,
                        StopsBySearchFragment.newInstance(getString(R.string.search_loc)))
                .addToBackStack(StopsBySearchFragment.TAG)
                .commit();
                break;
            case R.id.nav_maps:
                ft.replace(R.id.fragment_container,
                        StopsByMapFragment.newInstance(getString(R.string.search_map)))
                .addToBackStack(StopsByMapFragment.TAG)
                .commit();
                break;
            case R.id.nav_favourites:
                ft.replace(R.id.fragment_container,
                        StopsByFavoriteFragment.newInstance(getString(R.string.favorites)))
                .addToBackStack(StopsByFavoriteFragment.TAG)
                .commit();
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
    public void onLineInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(LinesBySearchListFragment.TAG)
                .replace(R.id.fragment_container, StopsByLineIdFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStopByLineInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopsByLineIdFragment.TAG)
                .replace(R.id.fragment_container, StopVisitFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStopBySearchInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopsByLineIdFragment.TAG)
                .replace(R.id.fragment_container, StopVisitFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStopByMapInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopsByLineIdFragment.TAG)
                .replace(R.id.fragment_container, StopVisitFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStopByFavoriteInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopsByLineIdFragment.TAG)
                .replace(R.id.fragment_container, StopVisitFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStopVisitInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(StopVisitFragment.TAG)
                .replace(R.id.fragment_container, StopsByLineIdFragment.newInstance(title, id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
