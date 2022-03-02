package com.questions.wayfair;

//The people who buy ads on our network don't have enough data about how ads are working for
//their business. They've asked us to find out which ads produce the most purchases on their website.

// Our client provided us with a list of user IDs of customers who bought something on a landing page
//after clicking one of their ads:

// # Each user completed 1 purchase.
// completed_purchase_user_ids = [
//   "3123122444","234111110", "8321125440", "99911063"]

// And our ops team provided us with some raw log data from our ad server showing every time a
//user clicked on one of our ads:
// ad_clicks = [
//  #"IP_Address,Time,Ad_Text",
//  "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
//  "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
//  "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
//  "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
//  "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
//  "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
//]

//The client also sent over the IP addresses of all their users.

//all_user_ips = [
//  #"User_ID,IP_Address",
//   "2339985511,122.121.0.155",
//  "234111110,122.121.0.1",
//  "3123122444,92.130.6.145",
//  "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
//  "8321125440,82.1.106.8",
//  "99911063,92.130.6.144"
//]

// Write a function to parse this data, determine how many times each ad was clicked,
//then return the ad text, that ad's number of clicks, and how many of those ad clicks
//were from users who made a purchase.


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Expected output:
// Bought Clicked Ad Text
// 1 of 2  2017 Pet Mittens
// 0 of 1  The Best Hollywood Coats
// 3 of 3  Buy wool coats for your pets
public class ADConventionRate {
    public static void main(String[] args) {
        String[] puchased_users = new String[] {
                "3123122444","234111110", "8321125440", "99911063"
        };

        String[] clicks = new String[] {
                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
        };

        String[] user_ips = new String[] {
                "2339985511,122.121.0.155",
                "234111110,122.121.0.1",
                "3123122444,92.130.6.145",
                "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8",
                "99911063,92.130.6.144"
        };

        printAddConventionRate(puchased_users, clicks, user_ips);
    }

    public static class Data {
        Set<String> visitedUsers = new HashSet<>();
        int purchasedUserCount = 0;

        public String getData() {
            return  purchasedUserCount+" of "+visitedUsers.size();
        }
    }

    public static void printAddConventionRate(String[] purchased_users, String[] clicks, String[] user_ips) {
        Map<String, String> ipUserMap = new HashMap<>();
        for(String userIp : user_ips) {
            String[] record = userIp.split(",");
            String ip = record[1].trim();
            String userId = record[0].trim();

            ipUserMap.put(ip, userId);
        }

        Map<String, Set<String>> adIpMap = new HashMap<>();
        for(String click : clicks) {
            String[] record = click.split(",");
            String ad = record[2].trim();
            String ip = record[0].trim();

            Set<String> set = adIpMap.getOrDefault(ad, new HashSet<>());
            set.add(ip);
            adIpMap.put(ad, set);
        }

        Set<String> purchasedUsers = new HashSet<>();
        for (String userId : purchased_users) {
            purchasedUsers.add(userId);
        }

        Map<String, Data> adUserMap = new HashMap<>();
        for(String ad : adIpMap.keySet()) {
            Data data = adUserMap.getOrDefault(ad, new Data());
            Set<String> ips = adIpMap.get(ad);
            for (String ip : ips) {
                String userId = ipUserMap.get(ip);
                data.visitedUsers.add(userId);
                if(purchasedUsers.contains(userId)) {
                    data.purchasedUserCount++;
                }
            }
            adUserMap.put(ad, data);
        }

        for(String ad : adUserMap.keySet()) {
            Data data = adUserMap.get(ad);
            System.out.println(data.getData() + " " + ad);
        }
    }
}
