package lw06.Unguided;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int target = 50;

        try {
            File file = new File("treenumbers.txt");
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().trim().split("\\s+");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        bst.insert(Integer.parseInt(token));
                    }
                }
            }

            if (scanner.hasNextInt()) {
                target = scanner.nextInt();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            int[] defaultNumbers = {60, 35, 80, 20, 45, 70, 90, 40, 45};
            for (int num : defaultNumbers) {
                bst.insert(num);
            }
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder  : ");
        bst.inOrder();

        System.out.print("Postorder: ");
        bst.postOrder();

        System.out.println("Search " + target + ": " + bst.search(target));
        System.out.println("Minimum  : " + bst.findMin());
        System.out.println("Maximum  : " + bst.findMax());
    }
}