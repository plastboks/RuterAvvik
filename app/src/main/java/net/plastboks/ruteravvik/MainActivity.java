package net.plastboks.ruteravvik;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
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
import android.widget.Toast;

import net.plastboks.ruteravvik.activity.AboutActivity;
import net.plastboks.ruteravvik.activity.SettingsActivity;
import net.plastboks.rutersugar.Ruter;
import net.plastboks.ruteravvik.fragment.*;
import net.plastboks.ruteravvik.storage.PersistentCache;
import net.plastboks.ruteravvik.storage.Settings;

public class MainActivity extends AppCompatActivity implements
            NavigationView.OnNavigationItemSelectedListener,
            LoadScreenFragment.OnLoadScreenDoneListener,
            LinesBySearchFragment.OnLineInteraction,
            StopsByLineIdFragment.OnStopByLineInteraction,
            StopsBySearchFragment.OnStopBySearchInteraction,
            StopsByMapFragment.OnStopByMapInteraction,
            StopVisitFragment.OnStopVisitInteraction,
            StopsByFavoriteFragment.OnStopByFavoriteInteraction
{

    public static final Ruter ruter = new Ruter("https://reisapi.ruter.no");
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            if (!PersistentCache.hasLines() && !PersistentCache.hasStops()) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, LoadScreenFragment.newInstance())
                        .commit();
            } else {
                loadFragment(true);
            }
        }
    }

    private void loadFragment(boolean emptyStack)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment;

        switch (Settings.getString("default_page")) {
            case "1":
                fragment = LinesBySearchFragment.newInstance(getString(R.string.search_line));
                break;
            case "2":
                fragment = StopsBySearchFragment.newInstance(getString(R.string.search_loc));
                break;
            case "3":
                fragment = StopsByFavoriteFragment.newInstance(getString(R.string.favorites));
                break;
            default:
                fragment = LinesBySearchFragment.newInstance(getString(R.string.search_line));
        }

        if (emptyStack) ft.add(R.id.fragment_container, fragment);
        else ft.replace(R.id.fragment_container, fragment);

        ft.commit();
    }

    public static void pushToast(int string, int duration)
    {
        Toast.makeText(getCurContext(),
                getCurContext().getString(string), duration).show();
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
                        LinesBySearchFragment.newInstance(getString(R.string.search_line)))
                .addToBackStack(LinesBySearchFragment.TAG)
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
    public void onLoadScreenDone() { loadFragment(false); }

    @Override
    public void onLineInteraction(String title, int id)
    {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(LinesBySearchFragment.TAG)
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

    public static Context getCurContext() { return mContext; }
}
