package net.plastboks.android.ruteravvik.util;

import net.plastboks.android.ruteravvik.model.Line;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TransportationTypeSplitter
{
    private List<Line> lines;
    private Map<Integer, ArrayList<Line>> splitted;

    public TransportationTypeSplitter(List<Line> lines)
    {
        this.lines = lines;
        splitted = new TreeMap<>();
        split();
    }

    private void split()
    {
        for (Line line : lines) {
           if (splitted.containsKey(line.getTransportation())) {
               splitted.get(line.getTransportation()).add(line);
           } else {
               ArrayList<Line> list = new ArrayList<>();
               list.add(line);
               splitted.put(line.getTransportation(), list);
           }
        }
    }

    public int count()
    {
        return splitted.size();
    }

    public Set<Integer> getKeys()
    {
        return splitted.keySet();
    }

    public Collection<ArrayList<Line>> getValues()
    {
        return splitted.values();
    }

    public Map<Integer, ArrayList<Line>> getSplitted()
    {
        return splitted;
    }
}
