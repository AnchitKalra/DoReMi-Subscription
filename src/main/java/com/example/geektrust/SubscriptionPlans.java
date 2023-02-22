package com.example.geektrust;

import java.util.*;

public class SubscriptionPlans {

    Map<String, Map<String, Integer>> map;
   List<String> hashSet;

   Map<String, Integer> monthMap;

    List<String> list;

    SubscriptionPlans() {
        map = new HashMap<>();
        monthMap = new HashMap<>();
        hashSet = new ArrayList<>();
        hashSet.add(Constants.FREE);
        hashSet.add(Constants.PERSONAL);
        hashSet.add(Constants.PREMIUM);
        monthMap.put(hashSet.get(0), 1);
        monthMap.put(hashSet.get(1), 1);
        monthMap.put(hashSet.get(2), 3);
        Map<String, Integer> hashMap;
        list = new ArrayList<>();
        list.add(Constants.MUSIC);
        list.add(Constants.VIDEO);
        list.add(Constants.PODCAST);
        for (String s : list) {
            hashMap = new HashMap<>();
            switch (s) {
                case Constants.MUSIC:
                    hashMap.put(hashSet.get(0), 0);
                    hashMap.put(hashSet.get(1), 100);
                    hashMap.put(hashSet.get(2), 250);
                    break;
                case Constants.VIDEO:
                    hashMap.put(hashSet.get(0), 0);
                    hashMap.put(hashSet.get(1), 200);
                    hashMap.put(hashSet.get(2), 500);
                    break;
                case Constants.PODCAST:
                    hashMap.put(hashSet.get(0), 0);
                    hashMap.put(hashSet.get(1), 100);
                    hashMap.put(hashSet.get(2), 300);
            }
            map.put(s, hashMap);
        }
    }
 }

