package net.plastboks.android.ruteravvik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.model.Stop;
import net.plastboks.android.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.List;

public class StopAdapter extends ArrayAdapter<StopAdapter.ListViewStop>
        implements Filterable
{

    private ArrayList<ListViewStop> fullList;
    private ArrayList<ListViewStop> mOriginalValues;

    private static class ViewHolder {
        TextView title;
        TextView subtitle;
        ImageView favoriteIcon;
        boolean isFavorite;
    }

    public static class ListViewStop
    {
        public final int id;
        public final String title;
        public final String subtitle;

        public final Stop stop;
        public final boolean favorite;

        public ListViewStop(Stop stop, boolean favorite)
        {
            this.id = stop.getId();
            this.title = String.format("%s", stop.getName());
            this.subtitle = String.format("%s%s",
                    stop.getDistrict(), stop.getZone().length() > 0 || stop.getZone().equals("0")
                            ? String.format(": %s", stop.getZone())
                            : "");

            this.stop = stop;
            this.favorite = favorite;
        }

    }

    public StopAdapter(Context context, List<ListViewStop> items)
    {
        super(context, R.layout.list_item_stop, items);
        this.fullList = (ArrayList<ListViewStop>)items;
        mOriginalValues = new ArrayList<>(fullList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.list_item_stop, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView)convertView.findViewById(R.id.item_title);
            viewHolder.subtitle = (TextView)convertView.findViewById(R.id.item_subtitle);
            viewHolder.favoriteIcon = (ImageView)convertView.findViewById(R.id.favorite_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        ListViewStop viewStop = getItem(position);
        viewHolder.title.setText(viewStop.title);
        viewHolder.subtitle.setText(viewStop.subtitle);
        viewHolder.isFavorite = viewStop.favorite;
        viewHolder.favoriteIcon.setImageDrawable(
                viewHolder.isFavorite
                        ? MainActivity.getCurContext().getResources()
                                    .getDrawable(R.drawable.ic_favorite_black_48dp)
                        : MainActivity.getCurContext().getResources()
                                    .getDrawable(R.drawable.ic_favorite_border_black_48dp));

        final String title = viewStop.title;
        final Stop stop = viewStop.stop;

        viewHolder.favoriteIcon.setOnClickListener(view ->
        {
                if (viewHolder.isFavorite) {
                    Toast.makeText(MainActivity.getCurContext(),
                            title + " " + MainActivity.getCurContext().getText(R.string.no_longer_fav),
                            Toast.LENGTH_SHORT).show();
                    Settings.removeFavorite(stop);
                    viewHolder.favoriteIcon.setImageDrawable(
                            MainActivity.getCurContext().getResources()
                                    .getDrawable(R.drawable.ic_favorite_border_black_48dp));
                    viewHolder.isFavorite = false;

                } else {
                    Toast.makeText(MainActivity.getCurContext(),
                            title + " " + MainActivity.getCurContext().getText(R.string.is_now_fav),
                            Toast.LENGTH_SHORT).show();
                    Settings.setFavorite(stop);
                    viewHolder.favoriteIcon.setImageDrawable(
                            MainActivity.getCurContext().getResources()
                                    .getDrawable(R.drawable.ic_favorite_black_48dp));
                    viewHolder.isFavorite = true;
                }
        });

        return convertView;
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults results = new FilterResults();

                if (constraint != null || constraint.length() != 0) {

                    List<ListViewStop> values = mOriginalValues;
                    List<ListViewStop> newValues = new ArrayList<>();
                    for (int i = 0; i < values.size(); i++) {

                        if (values.get(i).title.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            newValues.add(values.get(i));
                        }
                    }
                    results.values = newValues;
                    results.count = newValues.size();

                } else {
                    List<ListViewStop> list = new ArrayList<>(mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                clear();
                if(results != null && results.count > 0) {
                    addAll((ArrayList<ListViewStop>)results.values);
                } else {
                    addAll(mOriginalValues);
                }
                notifyDataSetChanged();
            }
        };
    }


}
