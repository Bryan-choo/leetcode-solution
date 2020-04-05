package 设计;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2020 4-5
 */
public class LFU缓存_LFUCache_460_Hard {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 0 );

        cache.put(0,0);
        cache.get(0);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4
    }
}

class LFUCache {

    private int capacity;

    private Map<Integer, Integer> vals;

    private Map<Integer, LinkedList<Integer>> indices;

    private Map<Integer, Integer> counts;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        indices = new HashMap<>();
        counts = new HashMap<>();
    }

    private void updateKey(int key) {
        int usecounts = counts.getOrDefault(key, 0);
        counts.put(key, usecounts+1);
        LinkedList oldlist = indices.get(usecounts);
        oldlist.remove(Integer.valueOf(key));
        indices.put(usecounts, oldlist);
        LinkedList newlist = indices.getOrDefault(usecounts+1, new LinkedList<>());
        newlist.offerLast(key);
        indices.put(usecounts+1, newlist);
        counts.put(key, usecounts+1);
    }

    public int get(int key) {
        int val = vals.getOrDefault(key, -1);
        if (val == -1)
            return -1;
        updateKey(key);
        return val;
    }

    public void put(int key, int value) {
        if (!vals.containsKey(key) && vals.size() >= capacity) {
            //移除元素
            List<Map.Entry<Integer, LinkedList<Integer>>> entries = indices.entrySet().stream().filter(r -> {
                return r.getValue().size() > 0;
            }).collect(Collectors.toList());
            if (entries == null || entries.size() == 0)
                return;
            LinkedList<Integer> list = entries.stream().min((r1, r2)->{return r1.getKey() - r2.getKey();}).get().getValue();
            int removedkey = list.removeFirst();
            counts.remove(removedkey);
            vals.remove(removedkey);
        }
        vals.put(key, value);
        int usecount = counts.getOrDefault(key, 0);
        if (usecount == 0) {
            LinkedList list = indices.getOrDefault(1, new LinkedList<Integer>());
            list.offerLast(key);
            indices.put(1, list);
            counts.put(key, usecount+1);
        } else {
            updateKey(key);
        }
    }
}