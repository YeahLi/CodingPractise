package com.basics.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyHashTable {
    public static void main(String[] args) {
        //1. Define
        Map<String, Integer> map = new HashMap<>();
        //2. empty
        map.isEmpty();
        //3. put
        map.put("1", 1);
        //4. get
        Integer value = map.get("1");
        value = map.getOrDefault("2", 2);

        //5. containsKey(key), containsValue(key)
        map.containsKey("3");
        map.containsValue(3);

        //6. keySet
        Set<String> keySet = map.keySet();
        // traverse the map
        for (String key : keySet) {
            System.out.println(key + ":" + map.get(key));
        }

        //7. Collection<T> values()
        List<Integer> valuesList = (List<Integer>) map.values();

        //查查有多少重复的
        map.put("4", 4);
        map.put("5", 4);
        Set<Integer> valuesSet = (Set<Integer>) map.values();
        System.out.println("Number of duplicated items are " + (valuesList.size() - valuesSet.size()));

        //8. Map.Entry
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
