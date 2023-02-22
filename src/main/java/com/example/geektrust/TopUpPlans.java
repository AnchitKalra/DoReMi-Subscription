
package com.example.geektrust;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopUpPlans {
    List<String> list;
    Map<String, Map<String, Integer>> map;

    TopUpPlans() {
        list = new ArrayList<>();
        map = new HashMap<>();
        HashMap<String, Integer> hashMap;
        list.add(Constants.FOUR_DEVICE);
        list.add(Constants.TEN_DEVICE);
        for(String s : list) {
            hashMap = new HashMap<>();
            switch (s) {
                case Constants.FOUR_DEVICE:
                    hashMap.put(Constants.FOUR, 50);
                    map.put(Constants.FOUR_DEVICE, hashMap);
                    break;
                case Constants.TEN_DEVICE:
                    hashMap.put(Constants.TEN, 100);
                    map.put(Constants.TEN_DEVICE, hashMap);
            }
        }
    }
}
