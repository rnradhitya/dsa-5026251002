package lw03.Unguided;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        
        Map<String, Integer> courseMap = new LinkedHashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();
        int rejectedCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            String command = parts[0];
            String courseCode = parts[1];

            if (command.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0) {
                    rejectedCount++;
                } else {
                    if (!courseMap.containsKey(courseCode)) {
                        courseOrder.add(courseCode);
                        courseMap.put(courseCode, count);
                    } else {
                        int currentCount = courseMap.get(courseCode);
                        courseMap.put(courseCode, currentCount + count);
                    }
                }
            } else if (command.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);
                if (count <= 0 || !courseMap.containsKey(courseCode)) {
                    rejectedCount++;
                } else {
                    int currentCount = courseMap.get(courseCode);
                    if (currentCount >= count) {
                        courseMap.put(courseCode, currentCount - count);
                    } else {
                        rejectedCount++;
                    }
                }
            } else if (command.equals("CHECK")) {
                if (courseMap.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": " + courseMap.get(courseCode) + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }

        System.out.println("===== Enrollment Checks");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");
        for (String code : courseOrder) {
            int count = courseMap.get(code);
            System.out.println(code + ": " + count + " students");
        }
        System.out.println("Rejected operations: " + rejectedCount);
    }
}