package lw03.prelab;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Problem 1");
        List<String> playlist = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File("playlist.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ", 2);
                String command = parts[0];

                if (command.equals("ADD")) {
                    String song = parts[1];
                    playlist.add(song);
                } else if (command.equals("INSERT")) {
                    String[] subParts = parts[1].split(" ", 2);
                    int index = Integer.parseInt(subParts[0]);
                    String song = subParts[1];
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    String song = parts[1];
                    playlist.remove(song);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt tidak ditemukan!");
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("\n===== Problem 2 =====");
        Set<String> uniqueParticipants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try {
            Scanner fileScanner = new Scanner(new File("participants.txt"));
            while (fileScanner.hasNextLine()) {
                String name = fileScanner.nextLine().trim();
                if (!name.isEmpty()) {
                    boolean isAdded = uniqueParticipants.add(name);
                    if (!isAdded) {
                        duplicateCount++;
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt tidak ditemukan!");
        }

        System.out.println("Unique participants: " + uniqueParticipants.size());
        int index = 1;
        for (String participant : uniqueParticipants) {
            System.out.println(index + ". " + participant);
            index++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("\n===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            Scanner fileScanner = new Scanner(new File("inventory.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File inventory.txt tidak ditemukan!");
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}