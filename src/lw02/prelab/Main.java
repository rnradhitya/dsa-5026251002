package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Step 1: LinkedList untuk menyimpan semua transaksi dari file
        LinkedList<String[]> transactionList = new LinkedList<>();

        // Step 2: LinkedList untuk menyimpan data nasabah [nama, saldo]
        LinkedList<String[]> customerList = new LinkedList<>();

        // Membaca file transactions.txt
        try {
            File file = new File("src/lw02/prelab/transactions.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String name = parts[0];
                String type = parts[1];
                String amount = parts[2];

                // Simpan transaksi ke LinkedList transaksi
                transactionList.add(new String[]{name, type, amount});

                // Tambahkan nasabah jika belum ada di customerList (pertama kali muncul)
                boolean exists = false;
                for (String[] customer : customerList) {
                    if (customer[0].equals(name)) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    customerList.add(new String[]{name, "0"});
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan: " + e.getMessage());
            return;
        }

        // Step 3: Pindahkan semua transaksi ke Queue untuk pemrosesan FIFO
        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);

        // Step 4: Stack untuk menyimpan transaksi penarikan yang gagal
        Stack<String[]> failedStack = new Stack<>();

        // Proses Queue satu per satu
        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll(); // FIFO: ambil & hapus transaksi terdepan
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            // Cari data nasabah yang bersangkutan
            for (String[] customer : customerList) {
                if (customer[0].equals(name)) {
                    int currentBalance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        currentBalance += amount;
                        customer[1] = String.valueOf(currentBalance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > currentBalance) {
                            // Saldo tidak cukup: masukkan transaksi ke Stack
                            failedStack.push(currentTx);
                        } else {
                            currentBalance -= amount;
                            customer[1] = String.valueOf(currentBalance);
                        }
                    }
                    break;
                }
            }
        }

        // Step 5: Tampilkan saldo akhir seluruh nasabah
        System.out.println("=== Final Balances ===");
        for (String[] customer : customerList) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        // Tampilkan daftar transaksi gagal dari Stack (LIFO)
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop(); // LIFO: ambil elemen paling atas
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}