package com.example.geektrust;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Service {

    Map<String, String> subscriptionMap;
    Map<String, Integer> topUpMap;

    SubscriptionPlans subscriptionPlans;
    LocalDate date;
    TopUpPlans topUpPlans;

    boolean flag = false;

    Service() {
        subscriptionMap = new HashMap<>();
        topUpMap = new HashMap<>();
        subscriptionPlans = new SubscriptionPlans();
        topUpPlans = new TopUpPlans();
    }
    public LocalDate startSubscription(String dateString) throws Exception {

        try {
            String a[] = dateString.split("-");
            if(Integer.valueOf(a[1]) > 12) {
               // System.out.println("?");
                throw new Exception();
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = LocalDate.parse(dateString, dateTimeFormatter);
            return date;

        }
        catch (Exception e) {
            System.out.println("INVALID_DATE");
            flag = true;
            return null;

           // throw new Exception();
        }

    }

    public  Map<String, String> addSubscription(String category, String planName) {

        try{
        if(flag) {
            throw new NoSubscription("ADD_SUBSCRIPTION_FAILED INVALID_DATE");

        }

            if (subscriptionMap.containsKey(category)) {
               throw new DuplicateException("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
            }

            subscriptionMap.put(category, planName);
            return subscriptionMap;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Map<String, Integer> addTopUp(String name, Integer months) {
        try {
                if(flag) {
                    throw new Exception("ADD_TOPUP_FAILED INVALID_DATE");
                }
            if(topUpMap.containsKey(name)) {
                throw new TopUpException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
            }
            topUpMap.put(name, months);
            return topUpMap;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int printRenewal() throws Exception {
        int renewalAmount = 0;
        Set<String> renewalSet = new HashSet<>();
        if (subscriptionMap.isEmpty()) {
            throw new NoSubscription("SUBSCRIPTIONS_NOT_FOUND");
        }
        for (String s : subscriptionPlans.list) {
            if (subscriptionMap.containsKey(s)) {
              renewalSet.add(s);
            }
        }
        List<String> list = new ArrayList<>();
        LocalDate newDate;
        int i = 0;

        for (String s : renewalSet) {
            list.add(subscriptionMap.get(s));
            int renewalMonth = subscriptionPlans.monthMap.get(list.get(i));
            newDate = date.plusMonths(renewalMonth);
            newDate = newDate.minusDays(10);
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date newDateTime = java.sql.Date.valueOf(newDate);
            String finalRenewalDate = simpleDateFormat.format(newDateTime);
            System.out.println(Constants.RENEWAL_REMINDER + " " + s + " " + finalRenewalDate);
            int cost = subscriptionPlans.map.get(s).get(list.get(i));
            renewalAmount += cost;
            i++;
        }

            for (String s : topUpPlans.list) {
                int cost = 0;
                int months = 0;
                if(topUpMap.size() > 1) {
                    throw new TopUpException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
                }
                if (topUpMap.containsKey(s)) {
                    if (s.equalsIgnoreCase(Constants.FOUR_DEVICE)) {
                        cost = topUpPlans.map.get(s).get(Constants.FOUR);
                    } else {
                        cost = topUpPlans.map.get(s).get(Constants.TEN);
                    }
                    months = topUpMap.get(s);
                    cost *= months;
                    renewalAmount += cost;
                }

            }


        System.out.println("RENEWAL_AMOUNT" + " " + renewalAmount);
            return renewalAmount;
    }


}
