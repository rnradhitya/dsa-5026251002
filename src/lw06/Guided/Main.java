// package lw06.Guided;

// import java.util.Scanner;

// public class Main {

//     public static Node insert(Node root, int data) {
//         if (root == null) {
//             return ________;
//         }

//         if (________) {
//             root.left = insert(root.left, data);
//         } else if (________) {
//             root.right = ________;
//         }

//         return root;
//     }

//     public static boolean search(Node root, int target) {
//         if (________) {
//             return false;
//         }

//         if (________) {
//             return true;
//         } else if (________) {
//             return ________;
//         } else {
//             return ________;
//         }
//     }

//     public static void preorder(Node root) {
//         if (________) {
//             System.out.print(root.data + " ");
//             ________;
//             ________;
//         }
//     }

//     public static void inorder(Node root) {
//         if (root != null) {
//             ________;
//             System.out.print(root.data + " ");
//             ________;
//         }
//     }

//     public static void postorder(Node root) {
//         if (root != null) {
//             ________;
//             ________;
//             System.out.print(root.data + " ");
//         }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

//         // Write your main code here

//         sc.close();
//     }
// }}
