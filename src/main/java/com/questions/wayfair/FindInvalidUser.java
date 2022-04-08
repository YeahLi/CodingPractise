package com.questions.wayfair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We are working on a security system for a badged-access room in our company's building.
 * Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
 *
 * All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)
 *
 * All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)
 *
 * Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.
 * badge_records_1 = [
 * 	["Martha", "exit"],
 * 	["Paul", "enter"],
 * 	["Martha", "enter"],
 * 	["Martha", "exit"],
 * 	["Jennifer", "enter"],
 * 	["Paul", "enter"],
 * 	["Curtis", "exit"],
 * 	["Curtis", "enter"],
 * 	["Paul", "exit"],
 * 	["Martha", "enter"],
 * 	["Martha", "exit"],
 * 	["Jennifer", "exit"],
 * 	["Paul", "enter"],
 * 	["Paul", "enter"],
 * 	["Martha", "exit"],
 * ]
 * Expected output: ["Curtis", "Paul"], ["Martha", "Curtis"]
 * n: length of the badge records array
 */
public class FindInvalidUser {

    public static List<List<String>> findInvalidUsers(List<String[]> input) {
        Map<String, String> status = new HashMap<>();
        Set<String> invalidExit = new HashSet<>();
        Set<String> invalidEnter = new HashSet<>();
        for (String[] record : input) {
            String name = record[0];
            String action = record[1];

            if (action.equals("exit")) { //exit won't write into status map
                if (status.containsKey(name) && status.get(name).equals("enter")) {
                    status.remove(name);
                } else {
                    invalidEnter.add(name);
                }
            } else {
                if (status.containsKey(name) && status.get(name).equals("enter")) {
                    invalidExit.add(name);
                } else {
                    status.put(name, "enter");
                }
            }
        }
        //the left items in status are the people who exit without badge
        for (String name : status.keySet()) {
            invalidExit.add(name);
        }

        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(invalidExit));
        result.add(new ArrayList<>(invalidEnter));
        return result;
    }

    public static void main(String[] args) {
        List<String[]> input = new ArrayList<>();
        input.add(new String[]{"Martha", "exit"});
        input.add(new String[]{"Paul", "enter"});
        input.add(new String[]{"Martha", "enter"});
        input.add(new String[]{"Martha", "exit"});
        input.add(new String[]{"Martha", "exit"});
        input.add(new String[]{"Jennifer", "enter"});
        input.add(new String[]{"Paul", "enter"});
        input.add(new String[]{"Curtis", "exit"});
        input.add(new String[]{"Curtis", "enter"});
        input.add(new String[]{"Paul", "exit"});
        input.add(new String[]{"Martha", "enter"});
        input.add(new String[]{"Martha", "exit"});
        input.add(new String[]{"Jennifer", "exit"});
        input.add(new String[]{"Paul", "enter"});
        input.add(new String[]{"Paul", "enter"});
        input.add(new String[]{"Martha", "exit"});

        List<List<String>> result = findInvalidUsers(input);
        System.out.println(result.get(0));
        System.out.println(result.get(1));

        String[][] logs = {{"James", "1300"}, {"Martha", "1600"}, {"Martha", "1620"}, {"Martha", "1530"}};
        Map<String, List<Integer>> result2 = personLeaveWithinOneHour(logs);
        for (String name : result2.keySet()) {
            System.out.println(name + ":" + result2.get(name).toString());
        }
    }

    /*
    给 list of [name, time], time is string format: '1300' //下午一点
        return: list of names and the times where their swipe badges within one hour. if there are multiple intervals that satisfy the condition, return any one of them.
        name1: time1, time2, time3...
        name2: time1, time2, time3, time4, time5...
        example:
        input: [['James', '1300'], ['Martha', '1600'], ['Martha', '1620'], ['Martha', '1530']]
        output: {
        'Martha': ['1600', '1620', '1530']
        }
     */

    public static Map<String, List<Integer>> personLeaveWithinOneHour(String[][] input) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (String[] log : input) {
            String name = log[0];
            int time = Integer.parseInt(log[1]);

            List<Integer> list = map.getOrDefault(name, new ArrayList<>());
            list.add(time);
            map.put(name, list);
        }

        Map<String, List<Integer>> res = new HashMap<>();
        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            Collections.sort(list);

            int start = 0, end = 0;
            while (end < list.size()) {
                if (withinOneHour(list.get(start), list.get(end))) {
                    end++;
                    continue;
                }
                List<Integer> newList = list.subList(start, end);
                if (newList.size() > 1) {
                    res.put(key, newList);
                    break;
                }
                start = end;
            }
            List<Integer> newList = list.subList(start, end);
            if (newList.size() > 1) {
                res.put(key, newList);
                break;
            }
        }
        return res;
    }

    private static boolean withinOneHour(int time1, int time2) {
        int num1 = (time1/100) * 60 + (time1%100);
        int num2 = (time2/100) * 60 + (time2%100);
        return num2 - num1 <= 60;
    }
}
