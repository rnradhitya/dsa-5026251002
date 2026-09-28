package lw02.Unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> requestList = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedStack = new Stack<>();

        bookList.add(new String[]{"Kalkulus", "2"});
        bookList.add(new String[]{"Fisika", "1"});
        bookList.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("src/lw02/Unguided/borrowing.txt"));

        while (scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requestList.add(request);

            String name = request[0];
            boolean foundMember = false;
            for (String[] member : memberList) {
                if (member[0].equals(name)) {
                    foundMember = true;
                    break;
                }
            }
            if (!foundMember) {
                memberList.add(new String[] { name, "0" });
            }
        }

        scanner.close();

       
        queue.addAll(requestList);

       
        LinkedList<String[]> successList = new LinkedList<>();
        int MAX_BORROW = 2;

     
        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String requestedBook = request[1];

          
            String[] targetBook = null;
            for (String[] book : bookList) {
                if (book[0].equals(requestedBook)) {
                    targetBook = book;
                    break;
                }
            }

            // Cari data member
            String[] targetMember = null;
            for (String[] member : memberList) {
                if (member[0].equals(name)) {
                    targetMember = member;
                    break;
                }
            }

            int stock = Integer.parseInt(targetBook[1]);
            int borrowedCount = Integer.parseInt(targetMember[1]);

          
            if (stock > 0 && borrowedCount < MAX_BORROW) {
             
                targetBook[1] = String.valueOf(stock - 1);
                targetMember[1] = String.valueOf(borrowedCount + 1);
                successList.add(request);
            } else {
             
                failedStack.push(request);
            }
        }

      
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] success : successList) {
            System.out.println(success[0] + " " + success[1]);
        }

       
        System.out.println("\n=== Remaining Book Stock ===");
        for (String[] book : bookList) {
            System.out.println(book[0] + ": " + book[1]);
        }

    
        System.out.println("\n=== Failed Requests ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1]);
        }
    }
}