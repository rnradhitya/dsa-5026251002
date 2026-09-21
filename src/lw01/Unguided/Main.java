package lw01.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Rental> jobs = new ArrayList<>();

        try {
            File file = new File("src/lw01/Unguided/jobs.txt");
            if (!file.exists()) {
                file = new File("jobs.txt");
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("Laptop")) {
                    jobs.add(new LaptopRental(id, pages));
                } else if (type.equalsIgnoreCase("Projector")) {
                    jobs.add(new ProjectorRental(id, pages));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File jobs.txt tidak ditemukan!");
            return;
        }

        for (Rental job : jobs) {
            System.out.println(job.summary());
        }
    }
}