package edu.sjsu.cmpe.cache.repository;

import edu.sjsu.cmpe.cache.domain.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ChronicleMap implements Cache {


    ConcurrentMap<Integer, CharSequence> concurrentMap;

    public ChronicleMap(ConcurrentMap<Integer, CharSequence> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public Entry saveEntry(Entry entry) {
        checkIfNotNull(entry, "entry instance must not be null");
        concurrentMap.putNew(entry.getKey(), entry.getValue());

        return entry;
    }

    @Override
    public Entry get(int key) {
        check(key > 0,
                "Key = %s; Expected value greater than zero.", key);
        Entry entry = new Entry();
        entry.setKey(key);
        entry.setValue(concurrentMap.get(key));
        return entry;
    }

    @Override
    public List<Entry> getAll() {
        List<Entry> entries = new ArrayList<Entry>();

        for(Map.Entry<Integer, CharSequence> entry: concurrentMap.entrySet()){
            Entry entryList = new Entry();
            entryList.setKey(entry.getKey());
            entryList.setValue(entry.getValue());
            entries.add(entryList);
        }
        return entries;
    }
}
