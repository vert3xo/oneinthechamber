package me.vert3xo.oitc.utils;

import java.util.*;

public class MapUtils {
    public static <K> Map<K, Integer> sortMapByValue(Map<K, Integer> map) {
        List<Map.Entry<K, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, Integer>>() {
            @Override
            public int compare(Map.Entry<K, Integer> o1, Map.Entry<K, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<K, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<K, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
