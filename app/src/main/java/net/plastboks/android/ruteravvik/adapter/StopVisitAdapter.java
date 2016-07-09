package net.plastboks.android.ruteravvik.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.model.Deviation;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.model.MonitoredVehicleJourney;
import net.plastboks.android.ruteravvik.storage.PersistentCache;
import net.plastboks.android.ruteravvik.storage.Settings;
import net.plastboks.android.ruteravvik.util.Datehelper;
import net.plastboks.android.ruteravvik.util.Mask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StopVisitAdapter extends ArrayAdapter<StopVisitAdapter.ListViewStopVisit>
        implements Filterable
{

    private ArrayList<ListViewStopVisit> fullList;
    private ArrayList<ListViewStopVisit> mOriginalValues;
    private boolean maskedMode = false;
    private Context context = App.getInstance().getApplicationContext();

    private static class ViewHolder {
        TextView num;
        TextView title;
        TextView platform;
        TextView departue;
        TextView devation;
        TextView colorSpace;
        ImageView maskIcon;
        boolean isMasked;
    }

    public static class ListViewStopVisit
    {
        public final String num;
        public final String title;
        public final String platform;
        public final String departure;
        public final String deviation;
        public final String bgColor;
        public final int id;
        public final int stationRef;
        public final Mask mask;
        public final List<Deviation> deviations;

        public ListViewStopVisit(MonitoredStopVisit monitoredStopVisit,
                                 Mask mask, int stationRef)
        {
            HashMap<String, String> colors = PersistentCache.getColors();
            MonitoredVehicleJourney transport = monitoredStopVisit.getMonitoredVehicleJourney();
            this.num = transport.getPublishedLineName();
            this.title = transport.getDestinationName();
            this.platform = String.format("%s",
                    transport.getMonitoredCall().getDeparturePlatformName());
            this.departure = Datehelper.getTimeSecond(
                    transport.getMonitoredCall().getExpectedDepartureTime());
            this.deviation = Datehelper.getTimeDiff(
                    transport.getMonitoredCall().getAimedDepartureTime(),
                    transport.getMonitoredCall().getExpectedDepartureTime()
            );
            this.bgColor = colors.get(transport.getPublishedLineName());
            this.mask = mask;
            this.id = Integer.parseInt(transport.getLineRef());
            this.stationRef = stationRef;
            this.deviations = monitoredStopVisit.getExtensions().getDeviations();
        }

    }

    public StopVisitAdapter(Context context, List<ListViewStopVisit> items)
    {
        super(context, R.layout.list_item_line, items);
        this.fullList = (ArrayList<ListViewStopVisit>)items;
        mOriginalValues = new ArrayList<>(fullList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.list_item_stopvisit, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.num = (TextView)convertView.findViewById(R.id.line_num);
            viewHolder.title = (TextView)convertView.findViewById(R.id.item_title);
            viewHolder.platform = (TextView)convertView.findViewById(R.id.item_platform);
            viewHolder.departue = (TextView)convertView.findViewById(R.id.item_departure);
            viewHolder.devation = (TextView)convertView.findViewById(R.id.item_deviation);
            viewHolder.colorSpace = (TextView)convertView.findViewById(R.id.color_space);
            viewHolder.maskIcon = (ImageView)convertView.findViewById(R.id.mask_icon);

            convertView.setTag(viewHolder);

        } else { viewHolder = (ViewHolder)convertView.getTag(); }

        final ListViewStopVisit line = getItem(position);
        viewHolder.num.setText(line.num);
        viewHolder.title.setText(line.title);
        viewHolder.platform.setText(line.platform);
        viewHolder.departue.setText(line.departure);
        viewHolder.devation.setText(line.deviation);
        viewHolder.isMasked = line.mask != null && line.mask.masked;

        if (line.bgColor != null) {
            try {
                viewHolder.colorSpace.setBackgroundColor(Color.parseColor("#" + line.bgColor));
            } catch (NumberFormatException nfe) {}
        }

        if (line.deviations != null && !line.deviations.isEmpty()) {
            viewHolder.maskIcon.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.ic_warning_black_48dp));
            viewHolder.maskIcon.setOnClickListener(view ->
            {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.deviation)
                            .setNegativeButton(R.string.close, null)
                            .setMessage(line.deviations.get(0).getHeader())
                            .setIcon(R.drawable.ic_warning_black_48dp)
                            .show();
            });
        } else { viewHolder.maskIcon.setImageDrawable(null); }

        if (maskedMode && line.mask != null) {

            viewHolder.maskIcon.setImageDrawable(context.getResources()
                    .getDrawable(line.mask.masked
                                        ? R.drawable.ic_visibility_off_black_48dp
                                        : R.drawable.ic_visibility_black_48dp));

            viewHolder.maskIcon.setOnClickListener(view ->
            {
                    String toastText = String.format("%s %s %s",
                            line.num, line.title, viewHolder.isMasked
                                    ? context.getString(R.string.no_longer_mask)
                                    : context.getString(R.string.is_now_masked));
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

                    viewHolder.maskIcon.setImageDrawable(context.getResources()
                                    .getDrawable(viewHolder.isMasked
                                                    ? R.drawable.ic_visibility_black_48dp
                                                    : R.drawable.ic_visibility_off_black_48dp));

                    if (viewHolder.isMasked) Settings.removeMask(line.mask.maskedRef);
                    else Settings.addMask(line.mask.maskedRef);

                    viewHolder.isMasked = !viewHolder.isMasked;
            });

        }

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

                    List<ListViewStopVisit> values = mOriginalValues;
                    List<ListViewStopVisit> newValues = new ArrayList<>();
                    for (int i = 0; i < values.size(); i++) {

                        String ref = String.format("%s %s",
                                values.get(i).num.toLowerCase(), values.get(i).title.toLowerCase());
                        if (ref.contains(constraint.toString().toLowerCase())) {
                            newValues.add(values.get(i));
                        }
                    }
                    results.values = newValues;
                    results.count = newValues.size();

                } else {
                    List<ListViewStopVisit> list = new ArrayList<>(mOriginalValues);
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
                    addAll((ArrayList<ListViewStopVisit>)results.values);
                } else {
                    addAll(mOriginalValues);
                }
                notifyDataSetChanged();
            }
        };
    }

    public void setMaskedMode(boolean mode)
    {
        maskedMode = mode;
    }

}
