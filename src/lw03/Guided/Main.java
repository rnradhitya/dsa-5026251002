package lw03.Guided;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playList = new ArrayList<>();
        while (sc1.hasNextLine()) {
            String line = sc1.nextLine();
            String operation = line.substring(0, line.indexOf(" "));
            String details = line.substring(line.indexOf(" ") + 1);

            if (operation.equals("ADD")) {
                String song = details;
                playList.add(song);
            } else if (operation.equals("INSERT")) {
                int index = Integer.parseInt(details.substring(0, details.indexOf(" ")));
                String song = details.substring(details.indexOf(" ") + 1);
                playList.add(index, song);
            } else {
                String song = details;
                if (playList.contains(song)) {
                    playList.remove(song);
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total: " + playList.size());
        int number = 1;
        for(String p: playList) {
            System.out.println(number + ": " + p);
            number++;
        }

        System.out.println();
        
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> students = new LinkedHashSet<>();
        int duplicate = 0;

        while (sc2.hasNextLine()) {
            String name = sc2.nextLine();
            if (students.contains(name)) {
                duplicate++;
            }
            students.add(name);
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants:" + students.size());
        int no = 1;
        for (String s : students) {
            System.out.println(no + ". " + s);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicate);

        System.out.println();

        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> products = new LinkedHashMap<>();
        int failedTrx = 0;

        while(sc3.hasNextLine()) {
            String line = sc3.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (products.containsKey(product)) {
                    int currentStock = products.get(product);
                    products.put(product, currentStock + qty);
                } else {
                    products.put(product, qty);
                }
            } else {
                if (products.containsKey(product) && products.get(product) >= qty){
                    int currentStock = products.get(product);
                    products.put(product, currentStock - qty);
                } else {
                    failedTrx++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (String p : products.keySet()) {
            System.out.println(p + ": " + products.get(p));
        }
        System.out.println("Failed sales: " + failedTrx);
    }
}