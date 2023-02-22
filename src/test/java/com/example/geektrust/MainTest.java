package com.example.geektrust;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;

public class MainTest {

    Service service = new Service();



    @Test
    public void testStartSubscription() throws Exception{
        String dateString = "10/12/2022";
        LocalDate date;
        date = service.startSubscription(dateString);
        Assertions.assertNull(date,"It's not null");
    }

    @Test
    public void testStartSubscroptionNotNull() throws Exception{
        String dateString = "10-12-2022";
       LocalDate date = service.startSubscription(dateString);
        Assertions.assertNotNull(date, "It's null");
    }

    @Test
    public void testAddSubscription() {
        String category = "MUSIC";
        String plan = "PERSONAL";
        Map<String, String> map;
        map = service.addSubscription(category, plan);
        Assertions.assertNotNull(map);
        Assertions.assertTrue(map.containsKey(category));
    }

    @Test
    public void testAddSubscriptionTrue() {
        String category = "Free";
        String plan = "Video";
        Map<String, String> map;
        map = service.addSubscription(category, plan);
        Assertions.assertTrue(map.containsKey(category));
    }

    @Test
    public void testAddTopUp() {
        String name = "FOUR_DEVICE";
        int months = 3;
        Map<String, Integer> map = service.addTopUp(name, months);
        Assertions.assertNotNull(map);
        Assertions.assertTrue(map.containsKey(name));

    }

    @Test
    public void testPrintRenewal() throws Exception{
        String dateString = "10-12-2022";
        service.startSubscription(dateString);
        String category = "MUSIC";
        String plan = "PERSONAL";
        service.addSubscription(category, plan);
        String name = "FOUR_DEVICE";
        int months = 3;
        service.addTopUp(name, months);
        int amount = service.printRenewal();
        Assertions.assertEquals(250, amount);
    }

    @Test
    public void testTopUpException() {
        TopUpException topUpException = new TopUpException("Test");
        Assertions.assertEquals("Test", topUpException.getMessage());

    }

    @Test
    public void testNoSubscription() {
        NoSubscription noSubscription = new NoSubscription("Test");
        Assertions.assertEquals("Test", noSubscription.getMessage());


    }

    @Test
    public void testDuplicateException() {
        DuplicateException duplicateException = new DuplicateException("Test");
        Assertions.assertEquals("Test", duplicateException.getMessage());


    }
}