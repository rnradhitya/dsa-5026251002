


package lw02.Unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Declare Linked list
        LinkedList<String[]> request = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        // Read File
        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("borrowing.txt"));

        // Uraian Parse ( Kalku, fisika, stat)
       bookList.add(new String[]{"Kalkulus", "2"});
       bookList.add(new String[]{"Fisika", "1"});
       bookList.add(new String[]{"Statistika", "2"});

        while (scanner.hasNext()) {
            String[] request = new String[3];
            request[0] = scanner.next();
            request[1] = scanner.next();
            request[2] = scanner.next();
            request.add(request);
        }

        scanner.close();
System.out.println(books);
    //     // LinkedList -> Queue
    //     queue.addAll(request);

    //     // Process transactions using FIFO
    //     while (!queue.isEmpty()) {

    //         String[] booklist = queue.poll();

    //         String name = transaction[0];
    //         String type = transaction[1];
    //         int amount = Integer.parseInt(transaction[2]);

    //         // Find customer
    //         String[] customer = null;

    //         for (String[] data : customers) {
    //             if (data[0].equals(name)) {
    //                 customer = data;
    //                 break;
    //             }
    //         }

    //         // Add new customer if not found
    //         if (customer == null) {
    //             customer = new String[] { name, "0" };
    //             customers.add(customer);
    //         }

    //         int balance = Integer.parseInt(customer[1]);

    //         if (type.equals("DEPOSIT")) {

    //             balance += amount;
    //             customer[1] = String.valueOf(balance);

    //         } else if (type.equals("WITHDRAW")) {

    //             if (amount <= balance) {

    //                 balance -= amount;
    //                 customer[1] = String.valueOf(balance);

    //             } else {

    //                 // Failed transaction -> Stack
    //                 failed.push(transaction);
    //             }
    //         }
    //     }

    //     // Final balances
    //     System.out.println("\n=== Final Balances ===");

    //     for (String[] customer : customers) {
    //         System.out.println(customer[0] + " : " + customer[1]);
    //     }

    //     // Failed transactions
    //     System.out.println("\n=== Failed Transactions ===");

    //     while (!failed.isEmpty()) {

    //         String[] transaction = failed.pop();

    //         System.out.println(
    //                 transaction[0] + " " +
    //                         transaction[1] + " " +
    //                         transaction[2]);
    //     }
    }
}