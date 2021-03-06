package com.questions.wayfair;


/*
Students may decide to take different "tracks" or sequences of courses in the Computer Science curriculum.
There may be more than one track that includes the same course, but each student follows a single linear track from a "root" node to a "leaf" node.
In the graph below, their path always moves left to right.

Write a function that takes a list of (source, destination) pairs, and returns the name of all of the courses that the students could be taking when they are halfway through their track of courses.

Sample input:
all_courses = [
    ["Logic", "COBOL"],
    ["Data Structures", "Algorithms"],
    ["Creative Writing", "Data Structures"],
    ["Algorithms", "COBOL"],
    ["Intro to Computer Science", "Data Structures"],
    ["Logic", "Compilers"],
    ["Data Structures", "Logic"],
    ["Creative Writing", "System Administration"],
    ["Databases", "System Administration"],
    ["Creative Writing", "Databases"],
    ["Intro to Computer Science", "Graphics"],
]

Sample output (in any order):
          ["Data Structures", "Creative Writing", "Databases", "Intro to Computer Science"]

All paths through the curriculum (midpoint *highlighted*):

*Intro to C.S.* -> Graphics
Intro to C.S. -> *Data Structures* -> Algorithms -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> Compiler
Creative Writing -> *Databases* -> System Administration
*Creative Writing* -> System Administration
Creative Writing -> *Data Structures* -> Algorithms -> COBOL
Creative Writing -> *Data Structures* -> Logic -> COBOL
Creative Writing -> *Data Structures* -> Logic -> Compilers

Visual representation:

                    ____________
                    |          |
                    | Graphics |
               ---->|__________|
               |                          ______________
____________   |                          |            |
|          |   |    ______________     -->| Algorithms |--\     _____________
| Intro to |   |    |            |    /   |____________|   \    |           |
| C.S.     |---+    | Data       |   /                      >-->| COBOL     |
|__________|    \   | Structures |--+     ______________   /    |___________|
                 >->|____________|   \    |            |  /
____________    /                     \-->| Logic      |-+      _____________
|          |   /    ______________        |____________|  \     |           |
| Creative |  /     |            |                         \--->| Compilers |
| Writing  |-+----->| Databases  |                              |___________|
|__________|  \     |____________|-\     _________________________
               \                    \    |                       |
                \--------------------+-->| System Administration |
                                         |_______________________|

Complexity analysis variables:

n: number of pairs in the input

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MidClass {
    public static void main(String[] args) {
        String[][] edges = new String[][] {
                {"Logic", "COBOL"},
                {"Data Structures", "Algorithms"},
                {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"},
                {"Intro to Computer Science", "Data Structures"},
                {"Logic", "Compilers"},
                {"Data Structures", "Logic"},
                {"Creative Writing", "System Administration"},
                {"Databases", "System Administration"},
                {"Creative Writing", "Databases"},
                {"Intro to Computer Science", "Graphics"}
        };
        Set<String> course = findMidCourse(edges);
        course.stream().forEach(System.out::println);
    }

    public static Set<String> findMidCourse(String[][] edges) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegreeMap = new HashMap<>();
        for(String[] edge :edges) {
            String start = edge[0];
            String end = edge[1];

            List<String> list = graph.getOrDefault(start, new ArrayList<>());
            list.add(end);
            graph.put(start, list);

            if(!indegreeMap.containsKey(start)) {
                indegreeMap.put(start, 0);
            }

            int count = indegreeMap.getOrDefault(end, 0);
            indegreeMap.put(end, count+1);
        }

        //dfs the graph to find all paths
        List<List<String>> res = new ArrayList<>();

        for(String node : indegreeMap.keySet()) {
            if(indegreeMap.get(node) == 0) {
                List<String> path = new ArrayList<>();
                path.add(node);
                //dfs
                findAllPaths(graph, node, path, res);
            }
        }

        //travers res and get the medium course
        Set<String> result = new HashSet<>();
        for(List<String> path : res) {
            int start = 0, end = path.size()-1;
            int mid = start + (end - start)/2;
            result.add(path.get(mid));
        }
        return result;
    }

    private static void findAllPaths(Map<String, List<String>> graph, String node, List<String> path, List<List<String>> res) {
        if (!graph.containsKey(node)) {
            res.add(new ArrayList<>(path));
            return;
        }

        List<String> nodes = graph.get(node);
        for(String next : nodes) {
            path.add(next);
            findAllPaths(graph, next, path, res);
            path.remove(path.size()-1);
        }
    }
}
