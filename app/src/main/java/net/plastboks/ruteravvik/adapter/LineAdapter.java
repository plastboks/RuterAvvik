package net.plastboks.ruteravvik.adapter;

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

import net.plastboks.rutersugar.domain.Deviation;
import net.plastboks.rutersugar.domain.Line;
import net.plastboks.rutersugar.domain.MonitoredStopVisit;
import net.plastboks.rutersugar.domain.MonitoredVehicleJourney;
import net.plastboks.ruteravvik.activity.MainActivity;
import net.plastboks.ruteravvik.util.Mask;
import net.plastboks.ruteravvik.R;
import net.plastboks.ruteravvik.util.Datehelper;
import net.plastboks.ruteravvik.util.TransportationType;
import net.plastboks.ruteravvik.storage.PersistentCache;
import net.plastboks.ruteravvik.storage.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LineAdapter extends ArrayAdapter<LineAdapter.ListViewLine>
        implements Filterable
{

    private ArrayList<ListViewLine> fullList;
    private ArrayList<ListViewLine> mOriginalValues;
    private boolean maskedMode = false;
    private Context mContext = MainActivity.getCurContext();

    private static class ViewHolder {
        TextView num;
        TextView title;
        TextView subtitle;
        TextView colorSpace;
        ImageView maskIcon;
        boolean isMasked;
    }

    public static class ListViewLine
    {
        public final String num;
        public final String title;
        public final String subtitle;
        public final String bgColor;
        public final int id;
        public final int stationRef;
        public final Mask mask;
        public final List<Deviation> deviations;

        public ListViewLine(MonitoredStopVisit monitoredStopVisit, Mask mask, int stationRef)
        {
            HashMap<String, String> colors = PersistentCache.getColors();
            MonitoredVehicleJourney transport = monitoredStopVisit.getMonitoredVehicleJourney();
            this.num = transport.getPublishedLineName();
            this.title = transport.getDestinationName();
            this.subtitle = String.format("%s (%s)",
                    Datehelper.getTimeSecond(transport.getMonitoredCall().getExpectedDepartureTime()),
                    Datehelper.getTimeDiff(
                            transport.getMonitoredCall().getAimedDepartureTime(),
                            transport.getMonitoredCall().getExpectedDepartureTime()
                    ));
            this.bgColor = colors.get(transport.getPublishedLineName());
            this.mask = mask;
            this.id = Integer.parseInt(transport.getLineRef());
            this.stationRef = stationRef;
            this.deviations = monitoredStopVisit.getExtensions().getDeviations();
        }

        public ListViewLine(Line line)
        {
            String title = String.format("%s",
                    TransportationType.getType(line.getTransportation()).getValue());
            this.num = line.getName();
            this.title = title;
            this.subtitle = "";
            this.bgColor = line.getLineColour();
            this.id = line.getId();
            this.stationRef = -1;
            this.mask = null;
            this.deviations = null;
        }
    }

    public LineAdapter(Context context, List<ListViewLine> items)
    {
        super(context, R.layout.list_item_line, items);
        this.fullList = (ArrayList<ListViewLine>)items;
        mOriginalValues = new ArrayList<>(fullList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.list_item_line, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.num = (TextView)convertView.findViewById(R.id.line_num);
            viewHolder.title = (TextView)convertView.findViewById(R.id.item_title);
            viewHolder.subtitle = (TextView)convertView.findViewById(R.id.item_subtitle);
            viewHolder.colorSpace = (TextView)convertView.findViewById(R.id.color_space);
            viewHolder.maskIcon = (ImageView)convertView.findViewById(R.id.mask_icon);

            convertView.setTag(viewHolder);

        } else { viewHolder = (ViewHolder)convertView.getTag(); }

        final ListViewLine line = getItem(position);
        viewHolder.num.setText(line.num);
        viewHolder.title.setText(line.title);
        viewHolder.subtitle.setText(line.subtitle);
        viewHolder.isMasked = line.mask != null && line.mask.masked;

        if (line.bgColor != null) {
            try {
                viewHolder.colorSpace.setBackgroundColor(Color.parseColor("#" + line.bgColor));
            } catch (NumberFormatException nfe) {}
        }

        if (line.deviations != null && !line.deviations.isEmpty()) {
            viewHolder.maskIcon.setImageDrawable(mContext.getResources()
                    .getDrawable(R.drawable.ic_warning_black_48dp));
            viewHolder.maskIcon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(mContext)
                            .setTitle(R.string.deviation)
                            .setNegativeButton(R.string.close, null)
                            .setMessage(line.deviations.get(0).getHeader())
                            .setIcon(R.drawable.ic_warning_black_48dp)
                            .show();
                }
            });
        } else { viewHolder.maskIcon.setImageDrawable(null); }

        if (maskedMode && line.mask != null) {

            viewHolder.maskIcon.setImageDrawable(mContext.getResources()
                    .getDrawable(line.mask.masked
                                        ? R.drawable.ic_visibility_off_black_48dp
                                        : R.drawable.ic_visibility_black_48dp));

            viewHolder.maskIcon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String toastText = String.format("%s %s %s",
                            line.num, line.title, viewHolder.isMasked
                                    ? mContext.getString(R.string.no_longer_mask)
                                    : mContext.getString(R.string.is_now_masked));
                    Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT).show();

                    viewHolder.maskIcon.setImageDrawable(mContext.getResources()
                                    .getDrawable(viewHolder.isMasked
                                                    ? R.drawable.ic_visibility_black_48dp
                                                    : R.drawable.ic_visibility_off_black_48dp));

                    if (viewHolder.isMasked) Settings.removeMask(line.mask.maskedRef);
                    else Settings.addMask(line.mask.maskedRef);

                    viewHolder.isMasked = !viewHolder.isMasked;
                }
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

                    List<ListViewLine> values = mOriginalValues;
                    List<ListViewLine> newValues = new ArrayList<>();
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
                    List<ListViewLine> list = new ArrayList<>(mOriginalValues);
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
                    addAll((ArrayList<ListViewLine>)results.values);
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
