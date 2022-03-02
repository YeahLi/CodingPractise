package com.questions.wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.
 * The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.
 * For example:
 * logs1 = [
 * ["58523", "user_1", "resource_1"],
 * ["62314", "user_2", "resource_2"],
 * ["54001", "user_1", "resource_3"],
 * ["200", "user_6", "resource_5"],
 * ["215", "user_6", "resource_4"],
 * ["54060", "user_2", "resource_3"],
 * ["53760", "user_3", "resource_3"],
 * ["58522", "user_22", "resource_1"],
 * ["53651", "user_5", "resource_3"],
 * ["2", "user_6", "resource_1"],
 * ["100", "user_6", "resource_6"],
 * ["400", "user_7", "resource_2"],
 * ["100", "user_8", "resource_6"],
 * ["54359", "user_1", "resource_3"],
 * ]
 * We would like to compute user sessions, specifically: write a function that takes the logs and returns a data structure that associates to each user their earliest and latest access times.
 * Example:
 * {'user_1': [54001, 58523],
 * 'user_2': [54060, 62314],
 * 'user_3': [53760, 53760],
 * 'user_5': [53651, 53651],
 * 'user_6': [2, 215],
 * 'user_7': [400, 400],
 * 'user_8': [100, 100],
 * 'user_22': [58522, 58522],
 * }
 * Example 2:
 * logs2 = [
 * ["300", "user_1", "resource_3"],
 * ["599", "user_1", "resource_3"],
 * ["900", "user_1", "resource_3"],
 * ["1199", "user_1", "resource_3"],
 * ["1200", "user_1", "resource_3"],
 * ["1201", "user_1", "resource_3"],
 * ["1202", "user_1", "resource_3"]
 * ]
 * Should return:
 * {'user_1': [300, 1202]}
 * Example 3:
 * logs3 = [
 * ["300", "user_10", "resource_5"]
 * ]
 * Should return:
 * {'user_10': [300, 300]}
 * Complexity analysis variables:
 * n: number of logs in the input
 */
public class UserLogsEarliestLatestAccessTime {

    public static class UserData {
        int earliest;
        int latest;
        public UserData() {
            earliest = Integer.MAX_VALUE;
            latest = 0;
        }
    }

    public static Map<String, List<Integer>> solution(List<String[]> logs) {
        Map<String, UserData> map = new HashMap<>();

        for (String[] log : logs) {
            String name = log[1];
            int time = Integer.parseInt(log[0]);

            UserData data = map.getOrDefault(name, new UserData());
            if (time < data.earliest) {
                data.earliest = time;
            }

            if (time > data.latest) {
                data.latest = time;
            }

            map.put(name, data);
        }

        Map<String, List<Integer>> result = map.entrySet().stream().collect(HashMap::new, (newMap, entry) -> {
            List<Integer> list = new ArrayList<>();
            list.add(entry.getValue().earliest);
            list.add(entry.getValue().latest);
            newMap.put(entry.getKey(), list);
        }, (newMap, entry) -> {});

        return result;
    }

    public static void main(String[] args) {
        List<String[]> log1 = new ArrayList<>();
        log1.add(new String[]{"58523", "user_1", "resource_1"});
        log1.add(new String[]{"62314", "user_2", "resource_2"});
        log1.add(new String[]{"54001", "user_1", "resource_3"});
        log1.add(new String[]{"200", "user_6", "resource_5"});
        log1.add(new String[]{"215", "user_6", "resource_4"});
        log1.add(new String[]{"54060", "user_2", "resource_3"});
        log1.add(new String[]{"53760", "user_3", "resource_3"});
        log1.add(new String[]{"58522", "user_22", "resource_1"});
        log1.add(new String[]{"53651", "user_5", "resource_3"});
        log1.add(new String[]{"2", "user_6", "resource_1"});
        log1.add(new String[]{"100", "user_6", "resource_6"});
        log1.add(new String[]{"400", "user_7", "resource_2"});
        log1.add(new String[]{"100", "user_8", "resource_6"});
        log1.add(new String[]{"54359", "user_1", "resource_3"});

        Map<String, List<Integer>> res = solution(log1);
        for (String key : res.keySet()) {
            System.out.println(key + ":" + res.get(key).toString());
        }

        System.out.println();

        List<String[]> log2 = new ArrayList<>();
        log2.add(new String[]{"300", "user_10", "resource_5"});

        res = solution(log2);
        for (String key : res.keySet()) {
            System.out.println(key + ":" + res.get(key).toString());
        }
    }
}
